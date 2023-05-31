package com.example.cexupuniversity.data.local

import androidx.room.*
import com.example.cexupuniversity.data.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMahasiswa(mahasiswa: List<Mahasiswa>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDosen(dosen: List<Dosen>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCourse(courses: List<Course>)

    @Delete()
    suspend fun deleteMahasiswa(mahasiswa: Mahasiswa)

    @Query("SELECT * FROM course")
    fun getAll(): Flow<List<Course>>

    @Transaction
    @Query("SELECT * FROM course WHERE courseId = :id")
    fun getAllMahasiswaWithCourseId(id: Int): Flow<List<CourseAndMahasiswa>>

    @Transaction
    @Query("SELECT * FROM course WHERE courseId = :id")
    fun getAllDosenWithCourseId(id: Int): Flow<List<CourseAndDosen>>

}