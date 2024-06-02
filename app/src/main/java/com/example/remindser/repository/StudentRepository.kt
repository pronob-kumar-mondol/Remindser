package com.example.remindser.repository

import androidx.lifecycle.LiveData
import com.example.remindser.Dao.StudentDao
import com.example.remindser.Entity.StudentTable
import com.google.firebase.database.DatabaseReference

class StudentRepository(private val studentDao: StudentDao, private val firebaseDatabase:DatabaseReference) {

    // Room methods
    suspend fun insertStudent(student: StudentTable) {
        studentDao.insertStudent(student)
    }

    suspend fun updateStudent(student: StudentTable) {
        studentDao.update(student)
    }

    suspend fun deleteStudent(student: StudentTable) {
        studentDao.delete(student)
    }

    fun getAllStudentsFromRoom(): LiveData<List<StudentTable>> {
        return studentDao.getAllStudents()
    }

    suspend fun deleteStudentById(id: Int) {
        studentDao.deleteById(id)
    }

    // Firebase methods
    // Implement methods to interact with Firebase Realtime Database
    // You would use Firebase's SDK methods here to perform these operations

    suspend fun getAllStudentsFromFirebase(): List<StudentTable> {
        // Implement logic to retrieve data from Firebase Realtime Database
        // You would typically use Firebase's APIs to perform this operation
        return listOf() // Placeholder
    }

    suspend fun insertStudentIntoFirebase(student: StudentTable) {
        // Implement logic to insert data into Firebase Realtime Database
    }

    suspend fun updateStudentInFirebase(student: StudentTable) {
        // Implement logic to update data in Firebase Realtime Database
    }

    suspend fun deleteStudentFromFirebase(student: StudentTable) {
        // Implement logic to delete data from Firebase Realtime Database
    }
}