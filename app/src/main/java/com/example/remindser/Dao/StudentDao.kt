package com.example.remindser.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.remindser.Entity.StudentTable

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: StudentTable)

    @Update
    suspend fun update(student: StudentTable)

    @Delete
    suspend fun delete(student: StudentTable)

    @Query("SELECT * FROM student_table ORDER BY id ASC")
    fun getAllStudents(): LiveData<List<StudentTable>>

    @Query("DELETE FROM student_table WHERE id = :id")
    suspend fun deleteById(id: Int)


}