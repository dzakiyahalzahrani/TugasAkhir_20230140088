package com.example.tugasakhir_20230140088.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tugasakhir_20230140088.data.entity.UserSettingsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserSettingsDao {

    @Query("SELECT * FROM user_settings WHERE user_id = :userId LIMIT 1")
    fun getSettingsByUser(userId: Int): Flow<UserSettingsEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSettings(settings: UserSettingsEntity)

    @Query("""
        UPDATE user_settings
        SET theme_name = :themeName,
            updated_at = :updatedAt
        WHERE user_id = :userId
    """)
    suspend fun updateTheme(
        userId: Int,
        themeName: String,
        updatedAt: Long
    )
}
