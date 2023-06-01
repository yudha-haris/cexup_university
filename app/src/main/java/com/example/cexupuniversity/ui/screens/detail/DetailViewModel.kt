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
    private val _mahasiswaNameInput = mutableStateOf("")
    private val _mahasiswaNimInput = mutableStateOf("")
    private val _errorText = mutableStateOf("")

    val uiStateMahasiswa: StateFlow<UiState<List<Mahasiswa>>>
        get() = _uiStateMahasiswa
    val query: State<String> get() = _query
    val courseAndDosen: State<CourseAndDosen> get() = _courseAndDosen
    val mahasiswaNameInput: State<String> get() = _mahasiswaNameInput
    val mahasiswaNimInput: State<String> get() = _mahasiswaNimInput
    val errorText: State<String> get() = _errorText

    fun insertMahasiswa(courseId: Int){

        val data = listOf(Mahasiswa(_mahasiswaNimInput.value, _mahasiswaNameInput.value, courseId))
        viewModelScope.launch {
            try{
                repository.insertMahasiswa(data)
                _mahasiswaNameInput.value = ""
                _mahasiswaNimInput.value = ""
            } catch(e: Exception) {
                _errorText.value = e.message.toString()
            }

        }
    }

    fun onInsertMahasiswaChanged(name: String, nim: String){
        _mahasiswaNimInput.value = nim
        _mahasiswaNameInput.value = name
        _errorText.value = ""
    }


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

    fun searchMahasiswa(id: Int, value: String){
        _query.value = value
        viewModelScope.launch {
            repository.getAllMahasiswaWithCourseId(id)
                .catch {
                    _uiStateMahasiswa.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _uiStateMahasiswa.value = UiState.Success(it[0].mahasiswas.filter { mhs ->
                        mhs.name.contains(_query.value, ignoreCase = true) || mhs.nim.contains(_query.value, ignoreCase = true)
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