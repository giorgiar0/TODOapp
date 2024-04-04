package com.example.todoapp

import android.content.Context
import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.cookies.cookies
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.*
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class KtorApiManager(private val context: Context) {

    private val client = HttpClient(Android) {
        install(HttpCookies)
        install(ContentNegotiation) {
            json()
        }
    }


    suspend fun signUp(user: SignUpActivity.User): HttpResponse {
        val url = "http://13.41.23.50/api/sign-up/start"


        Log.i("KtorApiManager", "Sending signup request with user: $user")
        Log.i("KtorApiManager", "Signup URL: $url")


        val response: HttpResponse = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(user)
        }

//        val response = client.post(url) {
//            contentType(ContentType.Application.Json)
//            setBody(Json.encodeToString(user))
//        }

        println(response.status)



        Log.d("KtorApiManager", "Signup response code: ${response.status}")


//        val cookies = client.cookies("http://13.41.23.50/")


        return response


//        val response: HttpResponse = client.post("")

    }


    suspend fun signIn(email: String, password: String, rememberMe: Boolean): HttpResponse {
        val url = "http://13.41.23.50/api/sign-in"

        data class Credentials(val email: String, val password: String, val rememberMe: Boolean)

        val credentials = Credentials(email, password, rememberMe)


        val response = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(credentials))
        }

        val cookies = client.cookies("http://13.41.23.50/")

        println(response.status)


        return response

    }


//    suspend fun callAPI(url: String): String?{
//
//        try {
//            val response = ktorClient.get(url)
//            if (response.status.value == 200){
////                return response.text
//                return response.toString()
//            } else {
//                Log.e("API", "ERROR: ${response.status.value}")
//                return null
//            }
//        } catch (e: Exception){
//            Log.e("API", "Error calling API: $e")
//            return null
//        }
//    }


}