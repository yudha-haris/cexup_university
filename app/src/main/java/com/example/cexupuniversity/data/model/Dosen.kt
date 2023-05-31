package com.example.cexupuniversity.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dosen(
    @PrimaryKey
    val nid: String,
    val name: String,
    val courseTeachId: Int,
)
