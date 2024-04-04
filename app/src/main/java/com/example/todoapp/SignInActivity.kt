package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import io.ktor.http.isSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SignInActivity : AppCompatActivity() {

    private lateinit var signInEmailET: EditText
    private lateinit var signInPasswordET: EditText
    private lateinit var signinBTN: Button
    private lateinit var signInSignUpBTN: Button
    private lateinit var signInForgotPasswordBTN: Button
    private lateinit var signInRememberMeCheckBox: CheckBox

    private lateinit var apiManager: KtorApiManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        init()
        listeners()

        apiManager = KtorApiManager(this)

    }


    private fun listeners() {


        signinBTN.setOnClickListener {

            val email = signInEmailET.text.toString()
            val password = signInPasswordET.text.toString()
            val rememberMe = signInRememberMeCheckBox.isChecked


            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }

            CoroutineScope(Dispatchers.IO).launch {
                val response = try {
                    apiManager.signIn(email, password, rememberMe)
                } catch (e: Exception){
                    runOnUiThread{
                        Toast.makeText(this@SignInActivity, "Sign in failed!", Toast.LENGTH_SHORT).show()
                    }
                    return@launch
                }

                if (response.status.isSuccess()){
                    runOnUiThread{
                        Toast.makeText(this@SignInActivity,"Sign in successful!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
                    }
                } else{
                    val errorMessage = response.status.value
                    runOnUiThread{
                        Toast.makeText(this@SignInActivity,errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            }
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