package com.example.todoapp

import com.example.todoapp.model.Task

class ActiveFragment : BaseTaskFragment() {



    override fun getStatus(): String = "Active"


    override fun getInitialTasks(): List<Task> {
        return listOf(
//            Task(5, "Title 5", "Description 5", "Medium", "Active", "Today"),
//            Task(6, "Title 6", "Description 6", "Low", "Active", "Tomorrow")
        )
    }


}

