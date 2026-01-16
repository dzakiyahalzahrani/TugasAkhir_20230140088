package com.example.tugasakhir_20230140088.data.repository

import com.example.tugasakhir_20230140088.data.dao.FocusSessionDao
import com.example.tugasakhir_20230140088.data.entity.FocusSessionEntity
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class FocusRepository(
    private val dao: FocusSessionDao
) {

    suspend fun saveSession(
        userId: Int,
        durationMinutes: Int,
        ambience: String,
        status: String,
        startedAt: Long,
        endedAt: Long?
    ) {

        // ⬇️ KONVERSI endedAt → yyyy-MM-dd
        val dateString = endedAt?.let {
            Instant
                .ofEpochMilli(it)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .toString()
        } ?: LocalDate.now().toString()

        dao.insert(
            FocusSessionEntity(
                userId = userId,
                durationMinutes = durationMinutes,
                ambienceSound = ambience,
                sessionStatus = status,
                date = dateString,          // ⬅️ INI YANG KURANG
                startedAt = startedAt,
                endedAt = endedAt
            )
        )
    }
}
