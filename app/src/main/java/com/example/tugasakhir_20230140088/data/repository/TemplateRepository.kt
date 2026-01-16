package com.example.tugasakhir_20230140088.data.repository

import com.example.tugasakhir_20230140088.data.dao.TimerTemplateDao
import com.example.tugasakhir_20230140088.data.entity.TimerTemplateEntity

class TemplateRepository(
    private val dao: TimerTemplateDao
) {

    suspend fun addTemplate(
        name: String,
        durationMinutes: Int
    ) {
        dao.insertTemplate(
            TimerTemplateEntity(
                name = name,
                durationMinutes = durationMinutes
            )
        )
    }

    suspend fun getTemplates(): List<TimerTemplateEntity> {
        return dao.getAllTemplates()
    }

    suspend fun deleteTemplate(id: Int) {
        dao.deleteTemplate(id)
    }

    suspend fun updateTemplate(
        id: Int,
        name: String,
        durationMinutes: Int
    ) {
        dao.updateTemplate(id, name, durationMinutes)
    }

}
