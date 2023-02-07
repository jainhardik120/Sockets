package com.jainhardik120.sockets

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.io.PrintWriter
import java.net.Socket


class MainActivity : AppCompatActivity() {
    class ClientThread(private val message: String) : Runnable {
        override fun run() {
            try {
                val client = Socket("192.168.88.104", 9155)
                val printwriter = PrintWriter(client.getOutputStream(), true)
                printwriter.write(message)
                printwriter.flush()
                printwriter.close()
                client.close()
                Log.d("myApp", "run: Successful")
            } catch (e: IOException) {
                Log.d("myApp", "run: " + e)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText = findViewById<EditText>(R.id.et_message)
        findViewById<Button>(R.id.button_send_message).setOnClickListener {
            val message = editText.text.toString()
            Thread(ClientThread(message)).start()
        }
    }
}