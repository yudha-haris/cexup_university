package com.example.cexupuniversity.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cexupuniversity.data.model.Course
import com.example.cexupuniversity.data.model.Dosen
import com.example.cexupuniversity.data.model.Mahasiswa

@Database(entities = [Course::class, Dosen::class, Mahasiswa::class], version = 1, exportSchema = false)
abstract class CourseDatabase : RoomDatabase() {

    abstract fun courseDao(): CourseDao

    companion object {
        @Volatile
        private var INSTANCE: CourseDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): CourseDatabase {
            if(INSTANCE == null) {
                synchronized(CourseDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        CourseDatabase::class.java, "course_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE as CourseDatabase
        }
    }
}