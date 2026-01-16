package com.example.tugasakhir_20230140088.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tugasakhir_20230140088.utils.ThemePreference

class ThemeViewModelFactory(
    private val pref: ThemePreference
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThemeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ThemeViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}
