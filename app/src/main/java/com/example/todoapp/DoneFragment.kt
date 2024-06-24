package com.example.todoapp

import com.example.todoapp.model.Task

class DoneFragment : BaseTaskFragment() {

    override fun getStatus(): String = "Done"


    override fun getInitialTasks(): List<Task> {
        return listOf(
//            Task(9, "Title 9", "Description 9", "Low", "Done", "Today"),
//            Task(10, "Title 10", "Description 10", "High", "Done", "Tomorrow")
        )
    }

}
