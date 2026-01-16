package com.example.tugasakhir_20230140088.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tugasakhir_20230140088.data.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserEntity)


    @Query("""
    SELECT * FROM users 
    WHERE LOWER(email) = LOWER(:email)
    AND password = :password
    LIMIT 1
""")
    suspend fun login(email: String, password: String): UserEntity?


    @Query("""
        SELECT * FROM users 
        WHERE email = :email 
        LIMIT 1
    """)
    suspend fun getUserByEmail(email: String): UserEntity?
}
