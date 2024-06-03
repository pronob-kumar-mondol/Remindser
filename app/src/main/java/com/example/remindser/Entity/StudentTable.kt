package com.example.remindser.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "student_table")
data class StudentTable(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val studentName:String="",
    val studentNumber:String="",
    val studentDescription:String="",
    val time:String="",
    val date:String=""
)
