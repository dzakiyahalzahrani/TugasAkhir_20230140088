package com.example.tugasakhir_20230140088.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhir_20230140088.data.entity.TimerTemplateEntity
import com.example.tugasakhir_20230140088.data.repository.TemplateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TemplateViewModel(
    private val repository: TemplateRepository
) : ViewModel() {

    private val _templates =
        MutableStateFlow<List<TimerTemplateEntity>>(emptyList())
    val templates: StateFlow<List<TimerTemplateEntity>> = _templates

    fun loadTemplates() {
        viewModelScope.launch {
            _templates.value = repository.getTemplates()
        }
    }

    fun addTemplate(name: String, duration: Int) {
        viewModelScope.launch {
            repository.addTemplate(name, duration)
            loadTemplates()
        }
    }
    fun deleteTemplate(id: Int) {
        viewModelScope.launch {
            repository.deleteTemplate(id)
            loadTemplates()
        }
    }

    fun updateTemplate(id: Int, name: String, duration: Int) {
        viewModelScope.launch {
            repository.updateTemplate(id, name, duration)
            loadTemplates()
        }
    }

}
