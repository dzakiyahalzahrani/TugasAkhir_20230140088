package com.example.tugasakhir_20230140088.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.tugasakhir_20230140088.data.repository.AuthRepository
import com.example.tugasakhir_20230140088.data.session.SessionManager

class AuthViewModel(
    private val repository: AuthRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    // =========================
    // REGEX VALIDATION
    // =========================
    private val emailRegex =
        Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")

    private val passwordRegex =
        Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.Login -> login(event.email, event.password)
            is AuthEvent.Register -> register(event.email, event.password)
            is AuthEvent.Logout -> logout()
        }
    }

    fun resetState() {
        _uiState.value = AuthUiState()
    }

    // =========================
    // LOGIN
    // =========================
    private fun login(email: String, password: String) {
        viewModelScope.launch {

            if (!emailRegex.matches(email)) {
                _uiState.value = AuthUiState(
                    errorMessage = "Email tidak valid (harus mengandung @)"
                )
                return@launch
            }

            if (!passwordRegex.matches(password)) {
                _uiState.value = AuthUiState(
                    errorMessage = "Password minimal 8 karakter dan harus mengandung huruf & angka"
                )
                return@launch
            }

            _uiState.value = AuthUiState(isLoading = true)

            val result = repository.login(email, password)

            _uiState.value = result.fold(
                onSuccess = {
                    AuthUiState(isSuccess = true)
                },
                onFailure = {
                    AuthUiState(errorMessage = it.message)
                }
            )
        }
    }

    // =========================
    // REGISTER
    // =========================
    private fun register(email: String, password: String) {
        viewModelScope.launch {

            if (!emailRegex.matches(email)) {
                _uiState.value = AuthUiState(
                    errorMessage = "Email harus menggunakan format yang valid (@)"
                )
                return@launch
            }

            if (!passwordRegex.matches(password)) {
                _uiState.value = AuthUiState(
                    errorMessage = "Password minimal 8 karakter dan mengandung huruf & angka"
                )
                return@launch
            }

            _uiState.value = AuthUiState(isLoading = true)

            val result = repository.register(email, password)

            _uiState.value = result.fold(
                onSuccess = {
                    AuthUiState(isRegistered = true)
                },
                onFailure = {
                    AuthUiState(errorMessage = it.message)
                }
            )
        }
    }

    // =========================
    // LOGOUT
    // =========================
    private fun logout() {
        viewModelScope.launch {
            sessionManager.clearSession()
            _uiState.value = AuthUiState()
        }
    }
}
