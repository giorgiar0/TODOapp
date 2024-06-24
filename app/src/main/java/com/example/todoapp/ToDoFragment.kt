package com.example.todoapp

import com.example.todoapp.model.Task

/*@Serializable
data class Task(
    val id: Int,
    val `when`: String,
    val title: String,
    val description: String,
    val priority: String,
    val status: String)*/

class ToDoFragment : BaseTaskFragment() {

/*
    private var tasks = mutableListOf<Task>()
    private var selectedStatus = "Todo"
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ToDoAdapter
    private lateinit var apiManager: KtorApiManager*/


    override fun getStatus(): String = "ToDo"


    override fun getInitialTasks(): List<Task> {
        return listOf(
//            Task(1, "Title 1", "Description 1", "High", "ToDo", "Today"),
//            Task(2, "Title 2", "Description 2", "Medium", "ToDo", "Tomorrow")
        )
    }

   /* override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val todoTaskList = view.findViewById<RecyclerView>(R.id.todo_task_list)

        val view = inflater.inflate(R.layout.fragment_todo, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_todo)

//        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_todo)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ToDoAdapter(tasks)
        recyclerView.adapter = adapter
        return view



//        todoTaskList = view.findViewById(R.id.todo_task_list)



//        val adapter = ToDoAdapter(tasks) { task -> showUpdateDialog(task) }

//        todoTaskList.adapter = ToDoAdapter(tasks) { task -> showUpdateDialog(task) }


//        apiManager = KtorApiManager(requireContext())
//        fetchTasks()


//        return view
    }*/





/*    private fun fetchTasks() {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                val fetchedTasks = apiManager.getTasks()
                withContext(Dispatchers.Main) {
                    tasks.clear()
                    tasks.addAll(fetchedTasks)
                    adapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                Log.e("ToDoFragment", "Error fetching tasks", e)
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Error fetching tasks", Toast.LENGTH_SHORT).show()
                }
            }



        }
    }*/



    private fun showUpdateDialog(task: Task) {

    }


}
