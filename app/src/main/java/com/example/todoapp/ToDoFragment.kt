package com.example.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

data class Task(val title: String, val description: String)


class ToDoFragment : Fragment() {

    private val tasks = listOf( // Sample task list, replace with your data source
        Task("Task 1", "Description for Task 1"),
        Task("Task 2", "Description for Task 2")
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_todo, container, false)
        val todoTaskList = view.findViewById<RecyclerView>(R.id.todo_task_list)

        todoTaskList.adapter = ToDoAdapter(tasks)


//         code to find views and handle tasks for the ToDo Section
        return view
    }
}




class ToDoAdapter(private val tasks: List<Task>) : RecyclerView.Adapter<ToDoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_task_item, parent, false)
        return ToDoViewHolder(view)

    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val task = tasks[position]
        holder.taskTitle.text = task.title
    }

    override fun getItemCount(): Int {
        return tasks.size
    }


}

class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val taskTitle: TextView = itemView.findViewById(R.id.task_title)


}






