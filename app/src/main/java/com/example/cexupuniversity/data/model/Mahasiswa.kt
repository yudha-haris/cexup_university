package com.example.cexupuniversity.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Mahasiswa(
    @PrimaryKey
    val nim: String,
    val name: String,
    val courseEnrollId: Int,
)
