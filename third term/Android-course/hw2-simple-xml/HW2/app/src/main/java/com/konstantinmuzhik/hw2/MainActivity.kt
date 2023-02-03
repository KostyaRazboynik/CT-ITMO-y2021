package com.konstantinmuzhik.hw2

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    private lateinit var warning: TextView
    private lateinit var password: EditText
    private lateinit var email: EditText
    private var modeCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        warning = findViewById(R.id.warning)
        password = findViewById(R.id.password)
        email = findViewById(R.id.email)

        password.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    logInClick()
                    return true
                }
                return false
            }
        })

        findViewById<Button>(R.id.logIn).setOnClickListener { logInClick() }

        var visibleCounter = 0

        val eye = findViewById<Button>(R.id.visible)
        eye.setOnClickListener {
            if (visibleCounter % 2 == 0) {
                password.transformationMethod = null
                eye.setBackgroundResource(R.drawable.hide)
            } else {
                password.transformationMethod = PasswordTransformationMethod()
                eye.setBackgroundResource(R.drawable.view)
            }
            visibleCounter++
        }

        findViewById<Button>(R.id.mode).setOnClickListener {
            if (modeCounter % 2 == 0) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            modeCounter++
        }
    }

    private fun logInClick() {
        if (email.text.toString().isEmpty() and password.text.toString().isEmpty())
            warning.text = getString(R.string.noLogNoPass)
        else if (email.text.toString().isEmpty() and password.text.toString().isNotEmpty())
            warning.text = getString(R.string.noLog)
        else if (email.text.toString().isNotEmpty() and password.text.toString().isEmpty())
            warning.text = getString(R.string.noPass)
        else
            warning.text = getString(R.string.inc)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(MODE, modeCounter.toString())
            putString(WAR, warning.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        warning.text = savedInstanceState.getString(WAR)
        modeCounter = savedInstanceState.getString(MODE)?.toInt() ?: 0
    }

    private companion object {
        const val WAR = "war"
        const val MODE = "mCounter"
    }
}