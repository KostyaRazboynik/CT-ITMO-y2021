package com.konstantinmuzhik.hw3

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var dataList: List<Contact>
    private val requestPer = 0
    private var permission = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_CONTACTS), requestPer)
        } else
            permission = true

        if (permission) {
            setContentView(R.layout.activity_main)
            dataList = fetchAllContacts()
            Toast.makeText(this, getString(R.string.num) + dataList.size, Toast.LENGTH_LONG).show()
            findViewById<RecyclerView>(R.id.recycler).adapter = ContactAdapter(this, dataList)
        } else {
            setContentView(R.layout.no_access)
            findViewById<Button>(R.id.retry).setOnClickListener { this.recreate() }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            requestPer -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permission = true
                    recreate()
                }
            }
        }
    }
}