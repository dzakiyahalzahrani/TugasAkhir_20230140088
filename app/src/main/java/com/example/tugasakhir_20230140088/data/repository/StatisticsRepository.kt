package com.example.tugasakhir_20230140088.data.repository

import com.example.tugasakhir_20230140088.data.dao.FocusSessionDao
import com.example.tugasakhir_20230140088.data.entity.FocusSessionEntity

class StatisticsRepository(
    private val dao: FocusSessionDao
) {

    suspend fun getSessionsByDate(date: String): List<FocusSessionEntity> {
        return dao.getSessionsByDate(date)
    }

    suspend fun getTotalMinutes(userId: Int): Int =
        dao.getTotalMinutes(userId)

    suspend fun getTotalSessions(userId: Int): Int =
        dao.getTotalSessions(userId)

    suspend fun getTotalMinutesByDate(userId: Int, date: String): Int =
        dao.getTotalMinutesByDate(userId, date)

    suspend fun getTotalSessionsByDate(userId: Int, date: String): Int =
        dao.getTotalSessionsByDate(userId, date)
}
