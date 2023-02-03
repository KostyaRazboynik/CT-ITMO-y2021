package com.konstantinmuzhik.hw4

import android.content.*
import android.os.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.konstantinmuzhik.hw4.Constants.INITIAL_SIZE
import com.konstantinmuzhik.hw4.Constants.LOAD_MESSAGES
import com.konstantinmuzhik.hw4.Constants.MAIN_ACTIVITY_TAG
import com.konstantinmuzhik.hw4.Constants.MESSAGE_SERVICE_TAG
import com.konstantinmuzhik.hw4.Constants.NEW_IMAGE
import com.konstantinmuzhik.hw4.Constants.NEW_MESSAGES
import com.konstantinmuzhik.hw4.Constants.POSITION
import com.konstantinmuzhik.hw4.Constants.SEND_IMAGE
import com.konstantinmuzhik.hw4.Constants.SEND_MESSAGE
import com.konstantinmuzhik.hw4.Constants.TEXT_STRING
import com.konstantinmuzhik.hw4.Constants.TYPE
import com.konstantinmuzhik.hw4.Constants.UPDATED_SIZE
import com.konstantinmuzhik.hw4.Constants.URI
import com.konstantinmuzhik.hw4.databinding.ActivityMainBinding
import com.konstantinmuzhik.hw4.services.MessageService

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivity: ActivityMainBinding
    private lateinit var recycler: RecyclerView
    private var messageService: MessageService? = null
    private var isBound = false


    private val boundServiceConnection: ServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binderBridge = service as MessageService.MyBinder
            messageService = binderBridge.getService()
            recycler.adapter = MessageDataAdapter(this@MainActivity, messageService!!.messages)
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
            messageService = null
        }
    }

    private val messageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            when (intent.getIntExtra(TYPE, -1)) {
                NEW_MESSAGES -> updateMessages(
                    intent.getIntExtra(INITIAL_SIZE, 0),
                    intent.getIntExtra(UPDATED_SIZE, 0)
                )
                NEW_IMAGE -> recycler.adapter?.notifyItemChanged(
                    intent.getIntExtra(POSITION, -1)
                )
                LOAD_MESSAGES -> recycler.scrollToPosition(messageService!!.messages.size - 1)
                else -> Toast.makeText(this@MainActivity, R.string.error, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun updateMessages(size: Int, newSize: Int) {
        Handler(Looper.getMainLooper()).post {
            recycler.post {
                recycler.adapter?.notifyItemRangeInserted(size, newSize)
            }
            val manager = recycler.layoutManager as LinearLayoutManager
            if (size != newSize && manager.findLastVisibleItemPosition() == size - 1) {
                recycler.smoothScrollToPosition(newSize - 1)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivity.root)

        // init recycler
        recycler = mainActivity.chatWindow
        val manager = LinearLayoutManager(this)
        recycler.apply {
            layoutManager = manager
            adapter = MessageDataAdapter(this@MainActivity, mutableListOf())
        }

        // init send button
        mainActivity.sendButton.setOnClickListener {
            val intent = Intent(MAIN_ACTIVITY_TAG)
            intent.putExtra(TYPE, SEND_MESSAGE)
            intent.putExtra(TEXT_STRING, mainActivity.newMessage.text.toString())
            mainActivity.newMessage.setText("")
            recycler.scrollToPosition(messageService!!.messages.size - 1)
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        }

        // init attachment button
        mainActivity.imageChoose.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            launchImageChoose.launch(intent)
            recycler.scrollToPosition(messageService!!.messages.size - 1)
        }

        val messageServiceIntent = Intent(this, MessageService::class.java)
        startService(messageServiceIntent)
        bindService(messageServiceIntent, boundServiceConnection, BIND_AUTO_CREATE)
    }

    private var launchImageChoose = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            data?.data?.let { selectedPhotoUri ->
                val intent = Intent(MAIN_ACTIVITY_TAG)
                intent.putExtra(TYPE, SEND_IMAGE)
                intent.putExtra(URI, selectedPhotoUri.toString())
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(messageReceiver, IntentFilter(MESSAGE_SERVICE_TAG))
    }

    override fun onStop() {
        super.onStop()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(messageReceiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound)
            unbindService(boundServiceConnection)
    }
}
