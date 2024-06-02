package com.example.remindser.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.remindser.Dao.StudentDao
import com.example.remindser.Entity.StudentTable

@Database(entities = [StudentTable::class], version = 1, exportSchema = false)
abstract class StudentDatabase:RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object{
        @Volatile
        private var INSTANCE: StudentDatabase?=null


        fun getDatabaseInstance(context: android.content.Context):StudentDatabase{
            if (INSTANCE == null){
                synchronized(StudentDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDatabase::class.java,
                        "student_db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }


}