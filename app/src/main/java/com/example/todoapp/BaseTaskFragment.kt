package com.example.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.model.Task

abstract class BaseTaskFragment : Fragment(){

    private val taskViewModel: TaskViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ToDoAdapter

    abstract fun getStatus(): String


    abstract fun getInitialTasks(): List<Task>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_tasks)
        recyclerView.layoutManager = LinearLayoutManager(context)


//        adapter = ToDoAdapter(mutableListOf())

        adapter = ToDoAdapter(mutableListOf()) { task ->
            showUpdateDialog(task)
        }

        recyclerView.adapter = adapter

        taskViewModel.getTasksByStatus(getStatus()).observe(viewLifecycleOwner, { tasks ->
            adapter.updateTasks(tasks)
        })



        if (taskViewModel.tasks.value.isNullOrEmpty()) {
            taskViewModel.updateTasks(getInitialTasks())
        }

        return view
    }

    fun addTask(task: Task) {
        taskViewModel.addTask(task)
    }

    fun updateTasks(newTasks: List<Task>) {
        taskViewModel.updateTasks(newTasks)
    }

    fun deleteTask(task: Task) {
        taskViewModel.deleteTask(task)
    }


    private fun showUpdateDialog(task: Task) {
        val dialog = CreateTaskDialogFragment(task)
        dialog.show(parentFragmentManager, "CreateTaskDialogFragment")
    }


}

