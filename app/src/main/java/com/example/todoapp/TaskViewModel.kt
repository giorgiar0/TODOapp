package com.example.todoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.todoapp.model.Task

class TaskViewModel : ViewModel() {


    private val _tasks = MutableLiveData<MutableList<Task>>(mutableListOf())


/*    private val _tasks: MutableLiveData<List<Task>> = MutableLiveData(listOf(
//        Task(1, "Title 1", "Description 1", "High", "ToDo", "Today"),
//        Task(2, "Title 2", "Description 2", "Medium", "ToDo", "Tomorrow")
    ))*/

    val tasks: LiveData<MutableList<Task>> get() = _tasks





    fun addTask(task: Task) {

        _tasks.value?.add(task)
        _tasks.value = _tasks.value


    }

    fun updateTask(updatedTask: Task) {
        val currentTasks = _tasks.value ?: mutableListOf()
        val taskIndex = currentTasks.indexOfFirst { it.id == updatedTask.id }

        if (taskIndex != -1) {
            currentTasks[taskIndex] = updatedTask
        } else {
            currentTasks.add(updatedTask)
        }

        _tasks.value = currentTasks


    }



    fun deleteTask(task: Task) {

        _tasks.value?.remove(task)
        _tasks.value = _tasks.value
    }

    fun getTasksByStatus(status: String): LiveData<List<Task>> {

        return _tasks.map { taskList ->
            taskList.filter { it.status.equals(status, ignoreCase = true) }
        }
    }

    fun updateTasks(newTasks: List<Task>) {

        _tasks.value = newTasks.toMutableList()
    }




}