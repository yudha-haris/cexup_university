package com.example.cexupuniversity.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cexupuniversity.data.CourseRepository
import com.example.cexupuniversity.data.model.Course
import com.example.cexupuniversity.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: CourseRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Course>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Course>>>
        get() = _uiState

    fun getAllCourses(){
        viewModelScope.launch {
            repository.getAllCourse()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    fun getData() = viewModelScope.launch {
        repository.insertAllData()
    }
}