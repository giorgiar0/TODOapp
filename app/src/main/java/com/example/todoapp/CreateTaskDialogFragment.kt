package com.example.todoapp

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.todoapp.model.Task
import java.util.Calendar

class CreateTaskDialogFragment(private val task: Task? = null) : DialogFragment() {


    interface OnTaskCreatedListener {
        fun onTaskCreated(task: Task)
    }


    private lateinit var listener: OnTaskCreatedListener


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnTaskCreatedListener

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialogView = requireActivity().layoutInflater.inflate(R.layout.dialog_add_task, null)
        val taskNameEditText = dialogView.findViewById<EditText>(R.id.edit_task_name)
        val taskDescriptionEditText = dialogView.findViewById<EditText>(R.id.edit_task_description)
        val prioritySpinner = dialogView.findViewById<Spinner>(R.id.spinner_priority)
        val statusSpinner = dialogView.findViewById<Spinner>(R.id.spinner_status)
        val datePickerButton = dialogView.findViewById<Button>(R.id.button_date_picker)
        val dateSelectedTextView = dialogView.findViewById<TextView>(R.id.text_date_selected)


        val priorityAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.priority_array,
            android.R.layout.simple_spinner_item
        )

        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        prioritySpinner.adapter = priorityAdapter



        val statusAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.status_array,
            android.R.layout.simple_spinner_item
        )
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        statusSpinner.adapter = statusAdapter



        var selectedDate = ""

        if (task != null) {
            taskNameEditText.setText(task.title)
            taskDescriptionEditText.setText(task.description)
            prioritySpinner.setSelection(priorityAdapter.getPosition(task.priority))
            statusSpinner.setSelection(statusAdapter.getPosition(task.status))
            selectedDate = task.`when`
            dateSelectedTextView.text = selectedDate
        }

        datePickerButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                R.style.CustomDatePickerDialog,
                { _, selectedYear, selectedMonth, selectedDay ->
                    selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                    dateSelectedTextView.text = selectedDate
                },
                year,
                month,
                day
            )


            datePickerDialog.setOnShowListener {
                datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)
                    ?.setTextColor(ContextCompat.getColor(requireContext(), R.color.sangu_color))
                datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)
                    ?.setTextColor(ContextCompat.getColor(requireContext(), R.color.sangu_color))
            }

            datePickerDialog.show()

        }

        val dialogBuilder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialogStyle)
            .setView(dialogView)
            .setTitle("Add New Task")
            .setPositiveButton("Add") { _, _ ->
                val taskName = taskNameEditText.text.toString()
                val taskDescription = taskDescriptionEditText.text.toString()
                val priority = prioritySpinner.selectedItem.toString()
                val status = statusSpinner.selectedItem.toString()
                val task = Task(
                    id = null,
                    title = taskName,
                    description = taskDescription,
                    priority = priority,
                    status = status,
                    `when` = selectedDate
                )
                listener.onTaskCreated(task)
            }
            .setNegativeButton("Cancel", null)

        val alertDialog = dialogBuilder.create()
        alertDialog.setOnShowListener {
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                ?.setTextColor(ContextCompat.getColor(requireContext(), R.color.sangu_color))
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                ?.setTextColor(ContextCompat.getColor(requireContext(), R.color.sangu_color))
        }





        return alertDialog


    }
}