package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var resetPassEmailET: EditText
    private lateinit var resetPassBTN: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        init()
        listeners()

    }


    private fun listeners() {


        resetPassBTN.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }


    }


    private fun init() {

        resetPassEmailET = findViewById(R.id.resetPassEmailET)
        resetPassBTN = findViewById(R.id.resetPassBTN)


    }


}