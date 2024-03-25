package com.example.todoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {

    private lateinit var homeSignOutBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        init()
        listeners()

    }


    private fun listeners() {


        homeSignOutBTN.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }


    }


    private fun init() {

        homeSignOutBTN = findViewById(R.id.homeSignOutBTN)


    }


}