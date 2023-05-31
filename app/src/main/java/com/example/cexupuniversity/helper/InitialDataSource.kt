package com.example.cexupuniversity.helper

import com.example.cexupuniversity.data.model.Course
import com.example.cexupuniversity.data.model.Dosen
import com.example.cexupuniversity.data.model.Mahasiswa

object InitialDataSource {

    fun getCourses(): List<Course> {
        return listOf(
            Course(0, "Dasar-Dasar Pemrograman 1"),
            Course(1, "Dasar-Dasar Pemrograman 2"),
            Course(2, "Struktur Data dan Algoritma"),
            Course(3, "Basis Data"),
            Course(4, "Analisis Numerik"),
        )
    }

    fun getDosen(): List<Dosen> {
        return listOf(
            Dosen("012345", "Fariz Darari, RAR.Net", 0),
            Dosen("123456", "Prof. Hilman", 1),
            Dosen("234567", "Prof. Mei Silviana Saputri", 2),
            Dosen("345678", "Prof. Iis Solichah", 3),
            Dosen("456789", "Prof. Tchan Basaruddin", 4),
        )
    }

    fun getMahasiswa(): List<Mahasiswa> {
        return listOf(
            Mahasiswa("0123", "Agus Supraptio", 0),
            Mahasiswa("1123", "Andy Rubin", 0),
            Mahasiswa("2123", "Rich Miner", 0),
            Mahasiswa("3123", "Tim Berners-Lee", 0),
            Mahasiswa("4123", "Robert Cailliau", 0),

            Mahasiswa("1234", "Arthur Samuel", 1),
            Mahasiswa("2234", "JCR Licklider", 1),
            Mahasiswa("3234", "Kakuzu", 1),
            Mahasiswa("4234", "Hidan", 1),
            Mahasiswa("5234", "Sasori", 1),

            Mahasiswa("2345", "Deidara", 2),
            Mahasiswa("3345", "Konan", 2),
            Mahasiswa("4345", "Obito", 2),
            Mahasiswa("5345", "Yahiko", 2),
            Mahasiswa("6345", "Nagato", 2),
            Mahasiswa("7345", "Zetsu", 2),

            Mahasiswa("3456", "Itachi", 3),
            Mahasiswa("4456", "Kisame", 3),
            Mahasiswa("5456", "Tony Stark", 3),
            Mahasiswa("6456", "Steve Roger", 3),
            Mahasiswa("7456", "Banner", 3),

            Mahasiswa("4567", "Wanda Maximoff", 4),
            Mahasiswa("5567", "Natasha Romanoff", 4),
            Mahasiswa("6567", "Carol Danver", 4),
            Mahasiswa("7567", "Peter Parker", 4),
            Mahasiswa("8567", "Charles Xavier", 4),
        )
    }


}