package com.example.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.todoapp.model.Task
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TaskSectionsFragment : Fragment() {

    private lateinit var subTabs: TabLayout
    private lateinit var subViewPager: ViewPager2
    private val taskViewModel: TaskViewModel by activityViewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_task_sections, container, false)
        subTabs = view.findViewById(R.id.sub_tabs)
        subViewPager = view.findViewById(R.id.sub_view_pager)

        val subSectionsAdapter = SubSectionsPagerAdapter(this)
        subViewPager.adapter = subSectionsAdapter

        TabLayoutMediator(subTabs, subViewPager) { tab, position ->
            tab.text = subSectionsAdapter.getPageTitle(position)
        }.attach()

        return view
    }

    fun updateTaskList(tasks: List<Task>) {
        for (fragment in childFragmentManager.fragments) {
            when (fragment) {
                is ToDoFragment -> fragment.updateTasks(tasks.filter { it.status.equals("todo", ignoreCase = true) })
                is BlockedFragment -> fragment.updateTasks(tasks.filter { it.status.equals("blocked", ignoreCase = true) })
                is ActiveFragment -> fragment.updateTasks(tasks.filter { it.status.equals("active", ignoreCase = true) })
                is CancelledFragment -> fragment.updateTasks(tasks.filter { it.status.equals("cancelled", ignoreCase = true) })
                is DoneFragment -> fragment.updateTasks(tasks.filter { it.status.equals("done", ignoreCase = true) })
            }
        }
    }


    fun addTaskToCorrectFragment(task: Task) {
        taskViewModel.addTask(task)
    }


    class SubSectionsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        private val fragmentTitles = listOf("ToDo", "Blocked", "Active", "Cancelled", "Done")

        override fun getItemCount(): Int {
            return fragmentTitles.size
        }

//        override fun getItemCount(): Int = fragmentTitles.size

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> ToDoFragment()
                1 -> BlockedFragment()
                2 -> ActiveFragment()
                3 -> CancelledFragment()
                4 -> DoneFragment()
                else -> Fragment()
            }
        }


        fun getPageTitle(position: Int): CharSequence? {
            return fragmentTitles[position]
        }

    }

}