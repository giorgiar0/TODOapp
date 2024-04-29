package com.example.todoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class HomeActivity : AppCompatActivity() {

    private lateinit var homeSignOutBTN: Button
    private lateinit var kanbanTabs: TabLayout
    private lateinit var kanbanBoardPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        init()
        listeners()

        val fragmentAdapter = SectionsPagerAdapter(this)

        kanbanBoardPager.adapter = fragmentAdapter

//        kanbanTabs.setupWithViewPager(kanbanBoardPager)


        kanbanTabs.apply {
            val mediator = TabLayoutMediator(this, kanbanBoardPager) { tab, position ->
                tab.text = fragmentAdapter.getPageTitle(position)
            }
            mediator.attach()
        }


    }


    class SectionsPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

        private val fragmentTitles = listOf("ToDo", "Blocked", "Active", "Cancelled", "Done")

        override fun getItemCount(): Int {
            return fragmentTitles.size
        }

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



    private fun listeners() {


        homeSignOutBTN.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }


    private fun init() {

        homeSignOutBTN = findViewById(R.id.homeSignOutBTN)
        kanbanTabs = findViewById(R.id.kanban_tabs)
        kanbanBoardPager = findViewById(R.id.kanban_board_pager)


    }
}