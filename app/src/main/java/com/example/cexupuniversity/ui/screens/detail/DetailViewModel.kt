package com.example.cexupuniversity.ui.screens.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cexupuniversity.data.CourseRepository
import com.example.cexupuniversity.data.model.*
import com.example.cexupuniversity.ui.common.UiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: CourseRepository) : ViewModel() {
    private val _uiStateMahasiswa: MutableStateFlow<UiState<List<Mahasiswa>>> = MutableStateFlow(UiState.Loading)
    private val _query = mutableStateOf("")
    private val _courseAndDosen = mutableStateOf(CourseAndDosen(
        course = Course(0, ""),
        dosen = Dosen("","", 0)
    ))

    val uiStateMahasiswa: StateFlow<UiState<List<Mahasiswa>>>
        get() = _uiStateMahasiswa
    val query: State<String> get() = _query
    val courseAndDosen: State<CourseAndDosen> get() = _courseAndDosen


    fun getAllMahasiswaWithCourseId(id: Int){
        viewModelScope.launch {
            repository.getAllMahasiswaWithCourseId(id)
                .catch {
                    _uiStateMahasiswa.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _uiStateMahasiswa.value = UiState.Success(it[0].mahasiswas)
                }
        }
    }

    fun getAllDosenWithCourseId(id: Int){
        viewModelScope.launch {
            repository.getAllDosenWithCourseId(id)
                .catch {
                    _uiStateMahasiswa.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _courseAndDosen.value = it[0]
                }
        }
    }

    fun getAllMahasiswaByName(id: Int, value: String){
        _query.value = value
        viewModelScope.launch {
            repository.getAllMahasiswaWithCourseId(id)
                .catch {
                    _uiStateMahasiswa.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _uiStateMahasiswa.value = UiState.Success(it[0].mahasiswas.filter { mhs ->
                        mhs.name.contains(_query.value, ignoreCase = true)
                    })
                }
        }
    }

    fun deleteMahasiswa(mahasiswa: Mahasiswa){
        viewModelScope.launch {
            repository.deleteMahasiswa(mahasiswa)
        }
    }
}