package com.example.cexupuniversity.data

import com.example.cexupuniversity.data.local.CourseDatabase
import com.example.cexupuniversity.data.model.Course
import com.example.cexupuniversity.data.model.CourseAndDosen
import com.example.cexupuniversity.data.model.CourseAndMahasiswa
import com.example.cexupuniversity.data.model.Mahasiswa
import com.example.cexupuniversity.helper.InitialDataSource
import kotlinx.coroutines.flow.Flow

class CourseRepository(private val courseDatabase: CourseDatabase) {
    fun getAllCourse() : Flow<List<Course>> = courseDatabase.courseDao().getAll()
    fun getAllMahasiswaWithCourseId(id: Int) : Flow<List<CourseAndMahasiswa>> = courseDatabase.courseDao().getAllMahasiswaWithCourseId(id)
    fun getAllDosenWithCourseId(id: Int) : Flow<List<CourseAndDosen>> = courseDatabase.courseDao().getAllDosenWithCourseId(id)

    suspend fun deleteMahasiswa(mahasiswa: Mahasiswa) = courseDatabase.courseDao().deleteMahasiswa(mahasiswa)
    suspend fun insertMahasiswa(mahasiswa: List<Mahasiswa>) = courseDatabase.courseDao().insertMahasiswa(mahasiswa)

    suspend fun insertAllData(){
        courseDatabase.courseDao().insertCourse(InitialDataSource.getCourses())
        courseDatabase.courseDao().insertDosen(InitialDataSource.getDosen())
        courseDatabase.courseDao().insertMahasiswa(InitialDataSource.getMahasiswa())
    }
}