package com.konstantinmuzhik.hw4

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.konstantinmuzhik.hw4.Constants.MESSAGE_POSITION
import com.konstantinmuzhik.hw4.databinding.ActivityImageBinding
import com.konstantinmuzhik.hw4.services.MessageService

class ImageActivity : AppCompatActivity() {

    private lateinit var imageActivity: ActivityImageBinding
    private var messageService: MessageService? = null
    private var isBound = false
    private val handler = Handler(Looper.getMainLooper())

    private val serviceConnection: ServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binderBridge: MessageService.MyBinder = service as MessageService.MyBinder
            messageService = binderBridge.getService()
            val position = intent.getIntExtra(MESSAGE_POSITION, -1)
            if (position != -1) {
                Thread {
                    handler.post {
                        imageActivity.fullImage.setImageBitmap(
                            messageService!!.getFullImage(position)
                        )
                    }
                }.start()
            }
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
            messageService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageActivity = ActivityImageBinding.inflate(layoutInflater)
        setContentView(imageActivity.root)

        val intent = Intent(this, MessageService::class.java)
        startService(intent)
        bindService(intent, serviceConnection, BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) {
            unbindService(serviceConnection)
        }
    }
}