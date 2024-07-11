package com.example.todoapp.model

import java.util.UUID

data class Task (
//    var id: Int? = null,
    val id: String = UUID.randomUUID().toString(),
    var title: String,
    var description: String,
    var priority: String,
    var status: String,
    var `when`: String
)