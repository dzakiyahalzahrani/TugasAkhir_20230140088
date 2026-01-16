package com.example.tugasakhir_20230140088.viewmodel

sealed class AuthEvent {
    data class Login(val email: String, val password: String) : AuthEvent()
    data class Register(val email: String, val password: String) : AuthEvent()
    object Logout : AuthEvent()
}
