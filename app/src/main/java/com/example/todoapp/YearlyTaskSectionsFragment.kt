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

class YearlyTaskSectionsFragment : Fragment() {
    private lateinit var subTabs: TabLayout
    private lateinit var subViewPager: ViewPager2
    private val taskViewModel: TaskViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_yearly_task_sections, container, false)
        subTabs = view.findViewById(R.id.sub_tabs)
        subViewPager = view.findViewById(R.id.yearly_sub_view_pager)

        val subSectionsAdapter = SubSectionsPagerAdapter(this)
        subViewPager.adapter = subSectionsAdapter

        TabLayoutMediator(subTabs, subViewPager) { tab, position ->
            tab.text = subSectionsAdapter.getPageTitle(position)
        }.attach()

        return view
    }

    fun addTaskToCorrectFragment(task: Task) {
        taskViewModel.addTask(task)
    }

    class SubSectionsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        private val fragmentTitles = listOf("ToDo", "Blocked", "Active", "Cancelled", "Done")

        override fun getItemCount(): Int = fragmentTitles.size


        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> YearlyToDoFragment()
                1 -> YearlyBlockedFragment()
                2 -> YearlyActiveFragment()
                3 -> YearlyCancelledFragment()
                4 -> YearlyDoneFragment()
                else -> Fragment()
            }
        }

        fun getPageTitle(position: Int): CharSequence? = fragmentTitles[position]

    }
}