package com.example.todoapp

import android.content.Context
import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import java.lang.Exception


class KtorApiManager(private val context: Context) {

    private val ktorClient = HttpClient()




    suspend fun callAPI(url: String): String?{

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
    }



}