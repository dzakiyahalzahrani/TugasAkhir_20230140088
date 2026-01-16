package com.example.tugasakhir_20230140088.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tugasakhir_20230140088.data.entity.FocusSessionEntity

@Dao
interface FocusSessionDao {

    @Insert
    suspend fun insert(session: FocusSessionEntity)

    // ===== TOTAL (SEMUA SESI) =====
    @Query("""
        SELECT IFNULL(SUM(durationMinutes), 0)
        FROM focus_sessions
        WHERE userId = :userId
    """)
    suspend fun getTotalMinutes(userId: Int): Int

    @Query("""
        SELECT COUNT(*)
        FROM focus_sessions
        WHERE userId = :userId
    """)
    suspend fun getTotalSessions(userId: Int): Int

    // ===== BY DATE (format date: YYYY-MM-DD) =====
    @Query("""
        SELECT IFNULL(SUM(durationMinutes), 0)
        FROM focus_sessions
        WHERE userId = :userId
        AND date(startedAt / 1000, 'unixepoch') = :date
    """)
    suspend fun getTotalMinutesByDate(
        userId: Int,
        date: String
    ): Int

    @Query("""
        SELECT COUNT(*)
        FROM focus_sessions
        WHERE userId = :userId
        AND date(startedAt / 1000, 'unixepoch') = :date
    """)
    suspend fun getTotalSessionsByDate(
        userId: Int,
        date: String
    ): Int

    @Query("""
    SELECT *
    FROM focus_sessions
    WHERE userId = :userId
    AND date(startedAt / 1000, 'unixepoch') = :date
    ORDER BY startedAt DESC
""")
    suspend fun getSessionsByDate(
        userId: Int,
        date: String
    ): List<FocusSessionEntity>

    @Query("SELECT * FROM focus_sessions WHERE date = :date")
    suspend fun getSessionsByDate(date: String): List<FocusSessionEntity>


}
