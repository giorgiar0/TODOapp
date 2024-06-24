package com.example.todoapp

import com.example.todoapp.model.Task

class BlockedFragment : BaseTaskFragment() {



    override fun getStatus(): String = "Blocked"


    override fun getInitialTasks(): List<Task> {
        return listOf(
//            Task(3, "Title 3", "Description 3", "Low", "Blocked", "Today"),
//            Task(4, "Title 4", "Description 4", "High", "Blocked", "Tomorrow")
        )
    }





}
