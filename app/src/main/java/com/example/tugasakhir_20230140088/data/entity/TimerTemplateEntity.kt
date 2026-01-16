package com.example.tugasakhir_20230140088.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timer_templates")
data class TimerTemplateEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val durationMinutes: Int
)
