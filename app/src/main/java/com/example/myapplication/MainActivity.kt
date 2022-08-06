package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button1= findViewById<Button>(R.id.button1)
        button1.setOnClickListener(){
            val intent= Intent(this,MainActivity4::class.java)
            startActivity(intent)
            finish()
        }
    }
}