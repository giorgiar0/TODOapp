package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText

class SignInActivity : AppCompatActivity() {

    private lateinit var SignInEmailET: EditText
    private lateinit var SignInPasswordET: EditText
    private lateinit var SigninBTN: Button
    private lateinit var SignInSignUpBTN: Button
    private lateinit var SignInForgotPasswordBTN: Button
    private lateinit var SignInRememberMeCheckBox: CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        init()
        listeners()


    }


    private fun listeners() {


        SigninBTN.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }


        SignInSignUpBTN.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }


        SignInForgotPasswordBTN.setOnClickListener {
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }


    }


    private fun init() {
        SignInEmailET = findViewById(R.id.SignInEmailET)
        SignInPasswordET = findViewById(R.id.SignInPasswordET)
        SigninBTN = findViewById(R.id.SigninBTN)
        SignInSignUpBTN = findViewById(R.id.SignInSignUpBTN)
        SignInForgotPasswordBTN = findViewById(R.id.SignInForgotPasswordBTN)
        SignInRememberMeCheckBox = findViewById(R.id.SignInRememberMeCheckBox)


    }


}