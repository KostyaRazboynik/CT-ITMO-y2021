package com.konstantinmuzhik.hw80


import android.animation.AnimatorInflater
import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var timer = Timer(200)
    private var main = Handler(Looper.getMainLooper())

    private var up: AndrewUpAnimation? = null
    private var down: AndrewDownAnimation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        up = findViewById(R.id.up)
        down = findViewById(R.id.down)

        (AnimatorInflater.loadAnimator(this, R.animator.text_animation) as ValueAnimator).apply {
            addUpdateListener { updatedAnimation ->
                findViewById<TextView>(R.id.text).rotationX = updatedAnimation.animatedValue as Float
            }

            start()
        }
    }

    private inner class Timer(val delay: Int) : Runnable {
        override fun run() {
            down!!.nextFrame()
            up!!.nextFrame()
            main.postDelayed(this, delay.toLong())
        }
    }

    override fun onStart() {
        super.onStart()
        main.post(timer)
    }

    override fun onStop() {
        super.onStop()
        main.removeCallbacks(timer)
    }
}