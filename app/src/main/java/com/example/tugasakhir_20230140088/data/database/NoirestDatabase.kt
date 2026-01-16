package com.example.tugasakhir_20230140088.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tugasakhir_20230140088.data.dao.UserDao
import com.example.tugasakhir_20230140088.data.entity.UserEntity
import com.example.tugasakhir_20230140088.data.entity.FocusSessionEntity
import com.example.tugasakhir_20230140088.data.dao.FocusSessionDao
import com.example.tugasakhir_20230140088.data.dao.TimerTemplateDao
import com.example.tugasakhir_20230140088.data.entity.UserSettingsEntity
import com.example.tugasakhir_20230140088.data.dao.UserSettingsDao
import com.example.tugasakhir_20230140088.data.entity.TimerTemplateEntity


@Database(
    entities = [
        UserEntity::class,
        FocusSessionEntity::class,
        UserSettingsEntity::class,
        TimerTemplateEntity::class
    ],
    version = 3, // <-- LANGKAH 1: Naikkan versi ke 2
    exportSchema = false
)
abstract class NoirestDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun focusSessionDao(): FocusSessionDao
    abstract fun userSettingsDao(): UserSettingsDao
    abstract fun timerTemplateDao(): TimerTemplateDao


    companion object {
        @Volatile
        private var INSTANCE: NoirestDatabase? = null

        fun getInstance(context: Context): NoirestDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoirestDatabase::class.java,
                    "noirest_database"
                )
                    // LANGKAH 2: Tambahkan baris ini untuk menangani migrasi
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
