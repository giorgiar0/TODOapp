package com.example.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.model.Task

class ToDoAdapter(private var tasks: MutableList<Task>,
                  private val onItemClicked: (Task) -> Unit
) : RecyclerView.Adapter<ToDoAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.taskTitle.text = task.title
        holder.taskDescription.text = task.description
        holder.taskPriority.text = task.priority
//        holder.taskStatus.text = task.status
        holder.taskWhen.text = task.`when`


        holder.itemView.setOnClickListener {
            onItemClicked(task)
        }
    }


    override fun getItemCount(): Int = tasks.size

    fun updateTasks(newTasks: List<Task>) {
        tasks.clear()
        tasks.addAll(newTasks)
        notifyDataSetChanged()
    }


    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskTitle: TextView = itemView.findViewById(R.id.task_title)
        val taskDescription: TextView = itemView.findViewById(R.id.task_description)
        val taskPriority: TextView = itemView.findViewById(R.id.task_priority)
//        val taskStatus: TextView = itemView.findViewById(R.id.task_status)
        val taskWhen: TextView = itemView.findViewById(R.id.task_when)
    }



}