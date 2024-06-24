package com.example.todoapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.todoapp.model.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class HomeActivity : AppCompatActivity(), CreateTaskDialogFragment.OnTaskCreatedListener {

    private lateinit var homeSignOutBTN: Button
    private lateinit var topTabs: TabLayout
    private lateinit var topViewPager: ViewPager2
    private lateinit var fabAddTask: FloatingActionButton


    private val taskViewModel: TaskViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        init()
        listeners()


        val topLevelAdapter = TopLevelPagerAdapter(this)
        topViewPager.adapter = topLevelAdapter
        topViewPager.isUserInputEnabled = false

        TabLayoutMediator(topTabs, topViewPager) { tab, position ->
            tab.text = topLevelAdapter.getPageTitle(position)
        }.attach()

    }


    private fun listeners() {


        homeSignOutBTN.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

        fabAddTask.setOnClickListener {
            showAddTaskDialog()
        }
    }



    private fun init() {

        homeSignOutBTN = findViewById(R.id.homeSignOutBTN)
        topTabs = findViewById(R.id.top_tabs)
        topViewPager = findViewById(R.id.top_view_pager)
        fabAddTask = findViewById(R.id.fab_add_task)


    }


    private fun showAddTaskDialog() {

        val dialog = CreateTaskDialogFragment()
        dialog.show(supportFragmentManager, "CreateTaskDialogFragment")



    }

    override fun onTaskCreated(task: Task) {


        val currentFragment = supportFragmentManager.findFragmentByTag("f${topViewPager.currentItem}") as? TaskSectionsFragment
        currentFragment?.addTaskToCorrectFragment(task)

        Log.d("HomeActivity", "Task added: $task")




    }

    class TopLevelPagerAdapter(activiy: FragmentActivity) : FragmentStateAdapter(activiy) {

        private val fragmentTitles = listOf("Tasks", "Yearly Tasks")

        override fun getItemCount(): Int = fragmentTitles.size


        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> TaskSectionsFragment()
                1 -> YearlyTaskSectionsFragment()
                else -> Fragment()
            }
        }

        fun getPageTitle(position: Int): CharSequence? = fragmentTitles[position]



    }



}
