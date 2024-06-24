package com.example.todoapp.model

data class Task (
    val id: Int? = null,
    val title: String,
    val description: String,
    val priority: String,
    val status: String,
    val `when`: String
)