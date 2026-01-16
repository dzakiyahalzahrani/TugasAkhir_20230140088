package com.example.tugasakhir_20230140088.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_settings",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],        // ⬅️ SESUAI UserEntity
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class UserSettingsEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "settings_id")
    val settingsId: Int = 0,

    @ColumnInfo(name = "user_id")
    val userId: Int,                     // ⬅️ FK ke UserEntity.id

    @ColumnInfo(name = "theme_name")
    val themeName: String = "Cozy",

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis()
)
