package com.example.tugasakhir_20230140088.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(
    tableName = "users",
    indices = [Index(value = ["email"], unique = true)]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val email: String,
    val password: String,

    val createdAt: Long = System.currentTimeMillis()
)
