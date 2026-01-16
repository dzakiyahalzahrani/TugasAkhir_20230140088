package com.example.tugasakhir_20230140088.viewmodel

data class AuthUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isRegistered: Boolean = false,
    val errorMessage: String? = null
)
