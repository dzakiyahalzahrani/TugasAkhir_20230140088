package com.example.tugasakhir_20230140088.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tugasakhir_20230140088.data.entity.TimerTemplateEntity

@Dao
interface TimerTemplateDao {

    @Insert
    suspend fun insertTemplate(template: TimerTemplateEntity)

    @Query("SELECT * FROM timer_templates")
    suspend fun getAllTemplates(): List<TimerTemplateEntity>

    @Query("DELETE FROM timer_templates WHERE id = :id")
    suspend fun deleteTemplate(id: Int)

    @Query("""
    UPDATE timer_templates 
    SET name = :name, durationMinutes = :duration 
    WHERE id = :id
""")
    suspend fun updateTemplate(
        id: Int,
        name: String,
        duration: Int
    )

}
