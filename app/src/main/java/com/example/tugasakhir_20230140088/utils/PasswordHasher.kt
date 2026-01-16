package com.example.tugasakhir_20230140088.utils

import java.security.MessageDigest

object PasswordHasher {

    fun hash(password: String): String {
        val bytes = password.toByteArray(Charsets.UTF_8)
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)

        return digest.joinToString("") {
            "%02x".format(it)
        }
    }
}
