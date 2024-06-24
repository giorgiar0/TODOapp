package com.example.todoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable


class SignUpActivity : AppCompatActivity() {

    private lateinit var signUpEmailEt: EditText
    private lateinit var signUpPasswordET: EditText
    private lateinit var signUpRepeatPasswordET: EditText
    private lateinit var signUpBTN: Button
    private lateinit var signUpAlreadyHaveAnAccountBTN: Button
    private lateinit var signUpAgreeCheckBox: CheckBox

    private lateinit var apiManager: KtorApiManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        init()
        listeners()

        apiManager = KtorApiManager(this)

    }

    private fun validatePassword(password: String): Boolean {
        if (password.length < 6 ||
            !password.matches("[a-zA-Z0-9!@#$%^&*()_+{}|:<>?]+".toRegex()) ||
            !password.contains(Regex("[a-z]")) ||
            !password.contains(Regex("[A-Z]")) ||
            !password.contains(Regex("[0-9]"))
        ) {
            return false
        }

        return true
    }

    private fun validateEmail(email: String): Boolean {
        return email.contains("@")
    }

    @Serializable
    data class User(val email: String, val password: String, val confirmPassword: String)


    private fun listeners() {

        signUpBTN.setOnClickListener {

            val email = signUpEmailEt.text.toString()
            val password = signUpPasswordET.text.toString()
            val confirmPassword = signUpRepeatPasswordET.text.toString()



            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {

                if (validateEmail(email) && validatePassword(password)) {

                    val user = User(email, password, confirmPassword)

                    lifecycleScope.launch {
                        val response = apiManager.signUp(user)

                        if (response.status == HttpStatusCode.OK) {
                            // Navigate to Sign In activity
                            startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
                            finish()  // Close SignUpActivity
                        } else {

                            val errorMessage = when (response.status) {
                                HttpStatusCode.BadRequest -> "Invalid signup data!"  // Assuming 400 for validation errors
                                HttpStatusCode.Conflict -> "User with this email already exists!"  // Assuming 409 for duplicate email


                                else -> "Signup failed: ${response.status}"
                            }

                            Toast.makeText(this@SignUpActivity, errorMessage, Toast.LENGTH_LONG)
                                .show()


                            println(response.status)
                        }
                    }
                } else {
                    if (!validateEmail(email)) {
                        val errorMessage = "Provide a valid email"
                        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                        return@setOnClickListener
                    } else if (!validatePassword(password)) {
                        val errorMessage =
                            "Password must be at least 6 characters and contain one lowercase, uppercase and digit."
                        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                        return@setOnClickListener
                    }
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
//        signUpAgreeCheckBox = findViewById(R.id.signUpAgreeCheckBox)
    }
}