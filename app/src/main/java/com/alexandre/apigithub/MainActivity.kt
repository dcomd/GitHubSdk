package com.alexandre.apigithub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sdkgit.ui.MainActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}