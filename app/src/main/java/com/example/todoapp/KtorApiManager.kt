package com.example.todoapp

import android.content.Context
import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.cookies.cookies
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json


class KtorApiManager(private val context: Context) {

    private val client = HttpClient(Android) {
        install(HttpCookies)
        install(ContentNegotiation) {
            json()
        }
        install(Logging)
        install(HttpTimeout){
            requestTimeoutMillis = 10000
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


        println(response.status)
        println(HttpStatusCode.Created)
        println(HttpStatusCode.OK)



        client.close()



        Log.d("KtorApiManager", "Signup response code: ${response.status}")


        val cookies = client.cookies("http://13.41.23.50/")


        return response


//        val response: HttpResponse = client.post("")

    }


    suspend fun signIn(credentials: SignInActivity.Credentials): HttpResponse {
        val url = "http://13.41.23.50/api/sign-in"

/*        data class Credentials(val email: String, val password: String, val rememberMe: Boolean)

        val credentials = Credentials(email, password, rememberMe)


        val response = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(credentials))
        }*/

        val response: HttpResponse = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(credentials)
        }

        val cookies = client.cookies("http://13.41.23.50/")

        println(response.status)


        return response

    }


/*    suspend fun getTasks():List<Task> {
        val url = "http://13.41.23.50/api/todos"

//        val response: HttpResponse = client.get(url)

        return try {
            val response: HttpResponse = client.get(url)

            if (response.status == HttpStatusCode.OK) {
                response.body()
            } else {
                val responseBody = response.body<String>()
                Log.e("KtorApiManager", "Error fetching tasks: ${response.status} - $responseBody")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("KtorApiManager", "Exception fetching tasks", e)
            emptyList()
        }


//        return if (response.status == HttpStatusCode.OK) {
//
//            response.body()
//        } else
//            emptyList()
    }*/


/*    suspend fun callAPI(url: String): String?{

        try {
            val response = ktorClient.get(url)
            if (response.status.value == 200){
//                return response.text
                return response.toString()
            } else {
                Log.e("API", "ERROR: ${response.status.value}")
                return null
            }
        } catch (e: Exception){
            Log.e("API", "Error calling API: $e")
            return null
        }
    }*/


}