package com.konstantinmuzhik.hw4.services

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.*
import android.provider.MediaStore
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.konstantinmuzhik.hw4.*
import com.konstantinmuzhik.hw4.Constants.IMAGE
import com.konstantinmuzhik.hw4.Constants.INITIAL_SIZE
import com.konstantinmuzhik.hw4.Constants.LOAD_MESSAGES
import com.konstantinmuzhik.hw4.Constants.MAIN_ACTIVITY_TAG
import com.konstantinmuzhik.hw4.Constants.MESSAGE_SERVICE_TAG
import com.konstantinmuzhik.hw4.Constants.NEW_IMAGE
import com.konstantinmuzhik.hw4.Constants.NEW_MESSAGES
import com.konstantinmuzhik.hw4.Constants.PATH
import com.konstantinmuzhik.hw4.Constants.POSITION
import com.konstantinmuzhik.hw4.Constants.RECIPIENT
import com.konstantinmuzhik.hw4.Constants.SEND_IMAGE
import com.konstantinmuzhik.hw4.Constants.SEND_MESSAGE
import com.konstantinmuzhik.hw4.Constants.TEXT
import com.konstantinmuzhik.hw4.Constants.TEXT_STRING
import com.konstantinmuzhik.hw4.Constants.TYPE
import com.konstantinmuzhik.hw4.Constants.UNKNOWN_ERROR
import com.konstantinmuzhik.hw4.Constants.UPDATED_SIZE
import com.konstantinmuzhik.hw4.Constants.URI
import com.konstantinmuzhik.hw4.Constants.USERNAME
import com.konstantinmuzhik.hw4.data.MyDatabase
import com.konstantinmuzhik.hw4.data.MyDelayedDatabase
import com.konstantinmuzhik.hw4.data.entity.DelayedMessageEntity
import com.konstantinmuzhik.hw4.data.entity.MessageEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.*
import java.net.URL
import java.util.*
import kotlin.math.roundToInt


class MessageService : Service() {

    private val sendMapper = JsonMapper
        .builder()
        .serializationInclusion(JsonInclude.Include.NON_NULL)
        .build()
        .registerModule(KotlinModule.Builder().build())

    private val networkWalker = Retrofit.Builder()
        .baseUrl(PATH)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(
            JacksonConverterFactory.create(
                JsonMapper
                    .builder()
                    .serializationInclusion(JsonInclude.Include.NON_NULL)
                    .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .build()
                    .registerModule(KotlinModule.Builder().build())
            )
        )
        .build()
        .create(RetrofitInterface::class.java)

    private val myScope = CoroutineScope(Dispatchers.IO)

    private val handler = Handler(Looper.myLooper()!!)

    val messages: MutableList<MessageData> = mutableListOf()
    private val messageDatabase by lazy { MyDatabase.get(this).messageDAO() }
    private val delayedMessageDatabase by lazy { MyDelayedDatabase.get(this).delayedMessageDAO() }

    private val sendMessageListener: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            when (intent.getIntExtra(TYPE, -1)) {
                SEND_MESSAGE -> preSendTextMessage(intent.getStringExtra(TEXT_STRING) ?: "")
                SEND_IMAGE -> preSendImageMessage(
                    Uri.parse(intent.getStringExtra(URI)) ?: Uri.EMPTY
                )
            }
        }
    }

    private var messageUpdater = object : Runnable {
        override fun run() {
            try {
                updateMessages()
            } finally {
                handler.postDelayed(this, 4000L)
            }
        }
    }

    private var imageUpdater = object : Runnable {
        override fun run() {
            try {
                updateImages()
            } finally {
                handler.postDelayed(this, 25000L)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        loadAll()
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(sendMessageListener, IntentFilter(MAIN_ACTIVITY_TAG))
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int = START_STICKY

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(messageUpdater)
        handler.removeCallbacks(imageUpdater)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(sendMessageListener)
    }

    override fun onBind(intent: Intent?): IBinder {
        return MyBinder()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    inner class MyBinder : Binder() {
        fun getService() = this@MessageService
    }

    private fun loadAll() {
        myScope.launch {

            messageDatabase.getAllMessages().forEach { messages += it.toData() }

            Intent(MESSAGE_SERVICE_TAG)
                .putExtra(TYPE, LOAD_MESSAGES)
                .putExtra(TEXT_STRING, "")

            messageUpdater.run()
            imageUpdater.run()
        }.start()
    }

    private fun updateMessages() {
        myScope.launch {
            try {
                delayedMessageDatabase.getAllDelayedMessages().forEach {
                    if (it.id == TEXT)
                        sendTextMessage(it.json)
                    else
                        preSendImageMessage(Uri.parse(it.uri))
                    delayedMessageDatabase.delete(it.id)
                }
            } catch (_: Exception) {
                makeToast(UNKNOWN_ERROR)
            }

            val newMessages = try {
                lastMessages()
            } catch (_: Exception) {
                mutableListOf()
            }
            val oldSize = messages.size
            newMessages.forEach {
                messageDatabase.saveMessage(it.toEntity(it.id!!))

                if (it.data.Image != null) {
                    it.data.Image.bitmap = compressImage(imageFromCache(it.id))
                }
                messages += it
            }

            if (newMessages.isNotEmpty()) {
                val newSize = messages.size
                LocalBroadcastManager.getInstance(this@MessageService).sendBroadcast(
                    Intent(MESSAGE_SERVICE_TAG)
                        .putExtra(TYPE, NEW_MESSAGES)
                        .putExtra(INITIAL_SIZE, oldSize)
                        .putExtra(UPDATED_SIZE, newSize)
                )
            }

            try {
                delayedMessageDatabase.getAllDelayedMessages().forEach {
                    if (it.id == TEXT)
                        sendTextMessage(it.json)
                    else
                        preSendImageMessage(Uri.parse(it.uri))
                    delayedMessageDatabase.delete(it.id)
                }
            } catch (_: Exception) {
                makeToast(UNKNOWN_ERROR)
            }

        }.start()
    }

    private fun lastMessages(): List<MessageData> {

        val call = if (messages.isNotEmpty()) {
            networkWalker.getMessages(messages.last().id!!.toInt()).execute()
        } else {
            networkWalker.getMessages(0).execute()
        }
        val tmp = if (call.code() != 200) {
            sendToast(call.code())
            mutableListOf()
        } else {
            call.body()
        }

        return tmp!!
    }

    /** TEXT MESSAGES */

    private fun preSendTextMessage(
        text: String,
        from: String = USERNAME,
        to: String = RECIPIENT
    ) {
        if (text.isNotEmpty()) {
            val message = MessageData(
                from,
                to,
                Data(Text = TextData(text)),
                Date().time.toString()
            )
            val json = sendMapper.writeValueAsString(message).replaceFirst("text", "Text")

            myScope.launch {
                try {
                    sendTextMessage(json)
                    updateMessages()
                } catch (_: Exception) {
                    delayedMessageDatabase.saveDelayedMessage(DelayedMessageEntity(TEXT, json, ""))
                }
            }.start()
        }
    }

    private fun sendTextMessage(json: String) {
        try {
            val answer = networkWalker.sendTextMessage(json).execute()
            println(answer)
            if (answer.code() != 200) {
                sendToast(answer.code())
            }
        } catch (_: Exception) {
            makeToast(UNKNOWN_ERROR)
        }
    }

    /** IMAGE MESSAGES */

    private fun updateImages() {
        myScope.launch {
            try {
                messages.forEachIndexed { index, it ->
                    it.data.Image ?: return@forEachIndexed
                    if (it.data.Image.bitmap == null) {
                        try {
                            imageToCache(it, it.id!!)
                            it.data.Image.bitmap = compressImage(imageFromCache(it.id))
                            LocalBroadcastManager.getInstance(this@MessageService).sendBroadcast(
                                Intent(MESSAGE_SERVICE_TAG)
                                    .putExtra(TYPE, NEW_IMAGE)
                                    .putExtra(POSITION, index)
                            )
                        } catch (_: Exception) {
                            return@forEachIndexed
                        }
                    }
                }
            } catch (_: Exception) {
                makeToast(UNKNOWN_ERROR)
            }
        }.start()
    }

    private fun preSendImageMessage(uri: Uri) {

        if (uri == Uri.EMPTY) return

        val image = getImageFromStorage(uri) ?: return

        val code = Date().time.toString()
        val file = try {
            getTempFile(image, code)
        } catch (_: Exception) {
            return
        }
        myScope.launch {
            try {
                sendImageMessage(file, code)
                updateMessages()
            } catch (_: Exception) {
                delayedMessageDatabase.saveDelayedMessage(
                    DelayedMessageEntity(
                        IMAGE,
                        "",
                        uri.toString()
                    )
                )
            } finally {
                file.delete()
            }
        }.start()
    }

    private fun sendImageMessage(file: File, code: String) {

        @Suppress("DEPRECATION")
        val requestBody =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
        val body = MultipartBody.Part.createFormData("picture", code, requestBody)
        val json = "{\"from\":\"$USERNAME\"}"
            .toRequestBody("application/json".toMediaTypeOrNull())

        try {
            val answer = networkWalker.sendImageMessage(body, json).execute()
            println(answer)
            if (answer.code() != 200) {
                sendToast(answer.code())
            }
        } catch (_: Exception) {
            makeToast(UNKNOWN_ERROR)
        }
    }

    private fun imageFromCache(imageId: Long?): Bitmap? {
        return try {
            val file = File(cacheDir, "$imageId.png")
            if (file.exists()) {
                BitmapFactory.decodeFile(file.absolutePath)
            } else {
                null
            }
        } catch (_: Exception) {
            null
        }
    }

    private fun compressImage(image: Bitmap?): Bitmap? {
        image ?: return null
        val ratio = image.width.toDouble() / image.height.toDouble()
        return Bitmap.createScaledBitmap(image, 400, (400 / ratio).roundToInt(), false)
    }

    fun getFullImage(position: Int): Bitmap? {
        return imageFromCache(messages[position].id)
    }

    private fun getTempFile(image: Bitmap, code: String): File {
        val file = File(this.cacheDir, "${code}temp.png")
        file.createNewFile()
        val byteArrayOutputStream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream)
        val bitmapData = byteArrayOutputStream.toByteArray()
        val fileOutputStream = FileOutputStream(file)
        fileOutputStream.write(bitmapData)
        fileOutputStream.flush()
        fileOutputStream.close()
        return file
    }

    private fun getImageFromStorage(uri: Uri): Bitmap? {
        return try {
            if (Build.VERSION.SDK_INT < 28) {
                @Suppress("DEPRECATION")
                MediaStore.Images.Media.getBitmap(
                    this.contentResolver,
                    uri
                )
            } else {
                ImageDecoder.decodeBitmap(ImageDecoder.createSource(this.contentResolver, uri))
            }
        } catch (_: Exception) {
            null
        }
    }

    private fun imageToCache(message: MessageData, imageId: Long) {

        val image = downloadFullImage(message.data.Image!!.link)
        image ?: return
        val file =
            File(this@MessageService.cacheDir, "$imageId.png").also { it.createNewFile() }
        val byteArrayOutputStream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream)
        val bitmapData = byteArrayOutputStream.toByteArray()
        FileOutputStream(file).use {
            with(it) {
                write(bitmapData)
                flush()
            }
        }
    }

    private fun downloadFullImage(link: String): Bitmap? {
        return downloadImage(URL("${PATH}img/$link"))
    }

    private fun downloadImage(url: URL): Bitmap? {
        return try {
            url.openStream().use {
                BitmapFactory.decodeStream(it)
            }
        } catch (_: Exception) {
            null
        }
    }

    /** MAPPER */

    private fun MessageEntity.toData(): MessageData {
        return if (this.text != null) {
            MessageData(
                id = this.id,
                from = this.from,
                to = this.to,
                data = Data(Text = TextData(this.text)),
                time = this.time
            )
        } else {
            MessageData(
                id = this.id,
                from = this.from,
                to = this.to,
                data = Data(
                    Image = ImageData(
                        link = this.link!!,
                        bitmap = compressImage(
                            imageFromCache(this.imageId)
                        )
                    )
                ),
                time = this.time
            )
        }
    }

    private fun MessageData.toEntity(imageId: Long): MessageEntity {
        if (this.data.Text != null) {
            return MessageEntity(
                this.id!!,
                null,
                this.from,
                this.to,
                this.data.Text.text,
                null,
                this.time
            )
        } else {
            return MessageEntity(
                this.id!!,
                imageId,
                this.from,
                this.to,
                null,
                this.data.Image!!.link,
                this.time
            )
        }
    }

    /** TOASTS */

    private fun makeToast(text: String) {
        val intent = Intent("Toast")
        intent.putExtra("text", text)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    private fun sendToast(code: Int) {
        when (code) {
            in 500..526 -> makeToast("Server error")
            400 -> makeToast("Everything is very bad")
            404 -> makeToast("Not found")
            409 -> makeToast("Conflict")
            411 -> makeToast("No Content-Length")
            413 -> makeToast("Too big picture")
            415 -> makeToast("Content-Type problems")
            422 -> makeToast("Data error")
            else -> makeToast(UNKNOWN_ERROR)
        }
    }
}
