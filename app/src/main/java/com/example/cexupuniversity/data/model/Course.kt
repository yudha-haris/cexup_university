package com.example.cexupuniversity.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class Course(
    @PrimaryKey
    val courseId: Int,
    val name: String,
)

data class CourseAndDosen(
    @Embedded val course: Course,
    @Relation(
        parentColumn = "courseId",
        entityColumn = "courseTeachId"
    )
    val dosen: Dosen
)

data class CourseAndMahasiswa(
    @Embedded val course: Course,
    @Relation(
        parentColumn = "courseId",
        entityColumn = "courseEnrollId"
    )
    val mahasiswas: List<Mahasiswa>
)