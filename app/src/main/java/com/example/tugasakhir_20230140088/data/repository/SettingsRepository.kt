package com.example.tugasakhir_20230140088.data.repository



import com.example.tugasakhir_20230140088.data.dao.UserSettingsDao
import com.example.tugasakhir_20230140088.data.entity.UserSettingsEntity
import kotlinx.coroutines.flow.Flow

class SettingsRepository(
    private val userSettingsDao: UserSettingsDao
) {

    fun getUserSettings(userId: Int): Flow<UserSettingsEntity?> {
        return userSettingsDao.getSettingsByUser(userId)
    }

    suspend fun initDefaultSettings(userId: Int) {
        val defaultSettings = UserSettingsEntity(
            userId = userId,
            themeName = "Cozy"
        )
        userSettingsDao.insertSettings(defaultSettings)
    }

    suspend fun updateTheme(userId: Int, themeName: String) {
        userSettingsDao.updateTheme(
            userId = userId,
            themeName = themeName,
            updatedAt = System.currentTimeMillis()
        )
    }
}
