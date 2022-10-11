package com.example.sleeptracker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sleep_table")
data class SleepEntity(
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "hoursSleep") val hoursSleep: String?,
    @ColumnInfo(name = "moodRating") val moodRating: String?,
    @ColumnInfo(name = "notes") val notes: String?,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)