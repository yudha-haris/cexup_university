package com.example.cexupuniversity.service

import android.content.Context
import com.example.cexupuniversity.data.CourseRepository
import com.example.cexupuniversity.data.local.CourseDatabase

object Injection {
    fun provideRepository(context: Context): CourseRepository {
        val courseDatabase = CourseDatabase.getDatabase(context)

        return CourseRepository(courseDatabase)
    }
}