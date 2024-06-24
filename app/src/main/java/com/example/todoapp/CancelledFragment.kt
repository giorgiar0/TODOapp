package com.example.todoapp

import com.example.todoapp.model.Task

class CancelledFragment : BaseTaskFragment() {


    override fun getStatus(): String = "Cancelled"


    override fun getInitialTasks(): List<Task> {
        return listOf(
//            Task(7, "Title 7", "Description 7", "High", "Cancelled", "Today"),
//            Task(8, "Title 8", "Description 8", "Medium", "Cancelled", "Tomorrow")
        )
    }

}
