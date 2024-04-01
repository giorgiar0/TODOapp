package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.content.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.*
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI


class SignUpActivity : AppCompatActivity() {

    private lateinit var signUpEmailEt: EditText
    private lateinit var signUpPasswordET: EditText
    private lateinit var signUpRepeatPasswordET: EditText
    private lateinit var signUpBTN: Button
    private lateinit var signUpAlreadyHaveAnAccountBTN: Button
    private lateinit var signUpAgreeCheckBox: CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        init()
        listeners()
    }

    private fun validatePassword(password: String): Boolean {
        if (password.length < 6 || !password.matches("[a-zA-Z0-9]+".toRegex()) || !password.contains(Regex("[a-z]")) || !password.contains(Regex("[A-Z]")) || !password.contains(Regex("[0-9]"))){
            return false
        }

        return true
    }

    private fun validateEmail(email: String):Boolean{
        return email.contains("@")
    }



    @OptIn(InternalAPI::class)
    private suspend fun registerUser(email: String, password: String) :Boolean {

        val url = ""

        val ktorClient = HttpClient{
            install(ContentNegotiation) {
                json()
            }
        }

        data class UserRegistrationData(val email: String, val password: String)


        val response = ktorClient.post(url){

            val hashedPassword = hashPassword
            val requestBody = UserRegistrationData(email = email, password = password)
            body = requestBody
        }

        if (response.status.value == 200) {
            val responseData = response.content
            val success = responseData.get("success") as Boolean
            return success
        } else {
            Log.e("API", "Registration failed: ${response.status.value}")
            return false
        }
    }




    private fun listeners() {


        signUpBTN.setOnClickListener {


            val email = signUpEmailEt.text.toString()
            val password = signUpPasswordET.text.toString()
            val repeatPassword = signUpRepeatPasswordET.text.toString()


            if (password != repeatPassword) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            if (!validatePassword(password)){
//                val errorMessage = "Password must be at least 6 characters and contain one lowercase, uppercase and digit."
//                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
//                return@setOnClickListener
//            }


            if (validateEmail(email) && validatePassword(password)){
                startActivity(Intent(this, SignInActivity::class.java))

            } else{
                if (!validateEmail(email)) {
                    val errorMessage = "Provide a valid email"
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                } else if (!validatePassword(password)){
                    val errorMessage = "Password must be at least 6 characters and contain one lowercase, uppercase and digit."
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

            }



        }

        signUpAlreadyHaveAnAccountBTN.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }


    }


    private fun init() {

        signUpEmailEt = findViewById(R.id.signUpEmailET)
        signUpPasswordET = findViewById(R.id.signUpPasswordET)
        signUpRepeatPasswordET = findViewById(R.id.signUpRepeatPasswordET)
        signUpBTN = findViewById(R.id.signUpBTN)
        signUpAlreadyHaveAnAccountBTN = findViewById(R.id.signUpAlreadyHaveAnAccountBTN)
        signUpAgreeCheckBox = findViewById(R.id.signUpAgreeCheckBox)


    }


}