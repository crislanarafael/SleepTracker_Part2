package com.example.sleeptracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.slider.Slider
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var sleeps: ArrayList<Sleep?>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sleeps = ArrayList<Sleep?>()

        val sleepsRV = findViewById<RecyclerView>(R.id.sleepRV)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val notesTaken = findViewById<EditText>(R.id.editText)
        val hoursSlept = findViewById<Slider>(R.id.sliderHours)
        val moodRating = findViewById<Slider>(R.id.sliderMood)

        val currentDate: String =
            SimpleDateFormat("EEEE, MMM d, yyyy", Locale.getDefault()).format(Date())
        val adapter = SleepAdapter(sleeps)
        sleepsRV.adapter = adapter


        lifecycleScope.launch {
            (application as SleepApplication).db.sleepDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    entity.date?.let {
                        entity.hoursSleep?.let { it1 ->
                            entity.moodRating?.let { it2 ->
                                entity.notes?.let { it3 ->
                                    Sleep(
                                        it,
                                        it1,
                                        it2,
                                        it3
                                    )
                                }
                            }
                        }
                    }
                }.also { mappedList ->
                    //articles.clear()
                    sleeps.addAll(mappedList)
                    adapter.notifyDataSetChanged()
                }
            }
        }




        sleepsRV.layoutManager = LinearLayoutManager(this).also{
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            sleepsRV.addItemDecoration(dividerItemDecoration)
        }




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

            sleeps.add(Sleep(date, hours, mood, notes))
            adapter.notifyDataSetChanged()


        }


    }
}


