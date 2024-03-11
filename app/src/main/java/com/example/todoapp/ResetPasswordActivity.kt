package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var ResetPassEmailET : EditText
    private lateinit var ResetPassBTN : Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        init()
        listeners()

    }



    private fun listeners(){


        ResetPassBTN.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }






    }



    private fun init(){

        ResetPassEmailET = findViewById(R.id.ResetPassEmailET)
        ResetPassBTN = findViewById(R.id.ResetPassBTN)




    }



}