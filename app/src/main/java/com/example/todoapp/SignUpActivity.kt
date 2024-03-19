package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText

class SignUpActivity : AppCompatActivity() {

    private lateinit var SignUpEmailEt: EditText
    private lateinit var SignUpPasswordET: EditText
    private lateinit var SignUpRepeatPasswordET: EditText
    private lateinit var SignUpBTN: Button
    private lateinit var SignUpAlreadyHaveAnAccountBTN: Button
    private lateinit var SignUpAgreeCheckBox: CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        init()
        listeners()
    }


    private fun listeners() {


        SignUpBTN.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

        SignUpAlreadyHaveAnAccountBTN.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }


    }


    private fun init() {

        SignUpEmailEt = findViewById(R.id.SignUpEmailET)
        SignUpPasswordET = findViewById(R.id.SignUpPasswordET)
        SignUpRepeatPasswordET = findViewById(R.id.SignUpRepeatPasswordET)
        SignUpBTN = findViewById(R.id.SignUpBTN)
        SignUpAlreadyHaveAnAccountBTN = findViewById(R.id.SignUpAlreadyHaveAnAccountBTN)
        SignUpAgreeCheckBox = findViewById(R.id.SignUpAgreeCheckBox)


    }


}