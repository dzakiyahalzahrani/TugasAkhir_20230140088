package com.example.tugasakhir_20230140088.data.repository

import com.example.tugasakhir_20230140088.data.dao.UserDao
import com.example.tugasakhir_20230140088.data.entity.UserEntity
import com.example.tugasakhir_20230140088.utils.PasswordHasher

class AuthRepository(
    private val userDao: UserDao
) {
    suspend fun ensureDefaultUser() {
        userDao.insertUser(
            UserEntity(
                id = 1,
                email = "offline@noirest.app",
                password = "offline"
            )
        )
    }

    suspend fun register(
        email: String,
        password: String
    ): Result<Unit> {

        val existingUser = userDao.getUserByEmail(email)
        if (existingUser != null) {
            return Result.failure(Exception("Email already registered"))
        }

        val hashedPassword = PasswordHasher.hash(password)

        userDao.insertUser(
            UserEntity(
                email = email,
                password = hashedPassword
            )
        )

        return Result.success(Unit)
    }

    suspend fun login(
        email: String,
        password: String
    ): Result<UserEntity> {

        val cleanEmail = email.trim().lowercase()
        val cleanPassword = password.trim()

        val hashedPassword = PasswordHasher.hash(cleanPassword)

        val user = userDao.login(cleanEmail, hashedPassword)

        return if (user != null) {
            Result.success(user)
        } else {
            Result.failure(Exception("Invalid email or password"))
        }
    }}

