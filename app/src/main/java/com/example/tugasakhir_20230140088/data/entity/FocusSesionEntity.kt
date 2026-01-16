package com.example.tugasakhir_20230140088.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "focus_sessions")
data class FocusSessionEntity(
    @PrimaryKey(autoGenerate = true)
    val sessionId: Int = 0,

    val userId: Int,

    val templateId: Int? = null,

    val durationMinutes: Int,

    val ambienceSound: String? = null,

    val sessionStatus: String = "COMPLETED", // COMPLETED / STOPPED

    val date: String,

    val startedAt: Long,

    val endedAt: Long?
)
