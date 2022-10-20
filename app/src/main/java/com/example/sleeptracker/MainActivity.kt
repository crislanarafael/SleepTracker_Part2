package com.example.sleeptracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.slider.Slider
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    //lateinit var sleeps: ArrayList<Sleep?>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //sleeps = ArrayList<Sleep?>()

        //val sleepsRV = findViewById<RecyclerView>(R.id.sleepRV)
        //val saveButton = findViewById<Button>(R.id.saveButton)
        //val notesTaken = findViewById<EditText>(R.id.editText)
        //val hoursSlept = findViewById<Slider>(R.id.sliderHours)
       // val moodRating = findViewById<Slider>(R.id.sliderMood)

       // val currentDate: String =
        //    SimpleDateFormat("EEEE, MMM d, yyyy", Locale.getDefault()).format(Date())

        val fragmentManager: FragmentManager = supportFragmentManager

        // define your fragments here
        val fragment1: Fragment = DashboardListFragment()
        val fragment2: Fragment = LogFragment()
        val fragment3: Fragment = HomeFragment()
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_dashboard -> fragment = fragment1
                R.id.nav_log -> fragment = fragment2
                R.id.nav_home -> fragment = fragment3

            }
            replaceFragment(fragment)
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.nav_dashboard








       /*
        saveButton.setOnClickListener {
            var date = currentDate
            var hours = "Slept " + hoursSlept.value.toString() + " hours"
            var mood = "Feeling " + moodRating.value.toString() + "/10"
            var notes = notesTaken.text.toString()

            var dbHours = hoursSlept.value.toString()
            var dbMood = moodRating.value.toString()

            lifecycleScope.launch(IO) {
                (application as SleepApplication).db.sleepDao().insert(
                    SleepEntity(date, dbHours, dbMood, notes)
                )
            }

            //sleeps.add(Sleep(date, hours, mood, notes))
            //adapter.notifyDataSetChanged()
        }*/


    }

    private fun replaceFragment(allFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.sleep_frame_layout, allFragment)
        fragmentTransaction.commit()
    }
}


