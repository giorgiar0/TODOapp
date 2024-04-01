package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class SignInActivity : AppCompatActivity() {

    private lateinit var signInEmailET: EditText
    private lateinit var signInPasswordET: EditText
    private lateinit var signinBTN: Button
    private lateinit var signInSignUpBTN: Button
    private lateinit var signInForgotPasswordBTN: Button
    private lateinit var signInRememberMeCheckBox: CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        init()
        listeners()


    }


    private fun listeners() {


        signinBTN.setOnClickListener {

            val email = signInEmailET.text.toString()
            val password = signInPasswordET.text.toString()


            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }






            startActivity(Intent(this, HomeActivity::class.java))
        }


        signInSignUpBTN.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }


        signInForgotPasswordBTN.setOnClickListener {
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }


    }


    private fun init() {
        signInEmailET = findViewById(R.id.signInEmailET)
        signInPasswordET = findViewById(R.id.signInPasswordET)
        signinBTN = findViewById(R.id.signinBTN)
        signInSignUpBTN = findViewById(R.id.signInSignUpBTN)
        signInForgotPasswordBTN = findViewById(R.id.signInForgotPasswordBTN)
        signInRememberMeCheckBox = findViewById(R.id.signInRememberMeCheckBox)


    }


}