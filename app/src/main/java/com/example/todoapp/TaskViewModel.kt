package com.example.todoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.todoapp.model.Task

class TaskViewModel : ViewModel() {


    private val _tasks: MutableLiveData<List<Task>> = MutableLiveData(listOf(
//        Task(1, "Title 1", "Description 1", "High", "ToDo", "Today"),
//        Task(2, "Title 2", "Description 2", "Medium", "ToDo", "Tomorrow")
    ))

    val tasks: LiveData<List<Task>> = _tasks

    fun addTask(task: Task) {

        _tasks.value = _tasks.value?.plus(task)

    }



    fun deleteTask(task: Task) {

        _tasks.value = _tasks.value?.filter { it.id != task.id }
    }


    fun updateTasks(newTasks: List<Task>) {

        _tasks.value = newTasks
    }

    fun getTasksByStatus(status: String): LiveData<List<Task>> {

        return _tasks.map { taskList ->
            taskList.filter { it.status.equals(status, ignoreCase = true) }
        }
    }
}