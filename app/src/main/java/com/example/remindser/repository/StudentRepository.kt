package com.example.remindser.repository

import androidx.lifecycle.LiveData
import com.example.remindser.Dao.StudentDao
import com.example.remindser.Entity.StudentTable
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class StudentRepository(private val studentDao: StudentDao, private val firestore:FirebaseFirestore) {

    // Room methods
    suspend fun insertStudent(student: StudentTable) {
        studentDao.insertStudent(student)
        insertStudentIntoFirestore(student)
    }

    suspend fun updateStudent(student: StudentTable) {
        studentDao.update(student)
        updateStudentInFirestore(student)
    }

    suspend fun deleteStudent(student: StudentTable) {
        studentDao.delete(student)
        deleteStudentFromFirestore(student)
    }

    fun getAllStudentsFromRoom(): LiveData<List<StudentTable>> {
        return studentDao.getAllStudents()
    }

    suspend fun deleteStudentById(id: Int) {
        studentDao.deleteById(id)
    }

    // Firestore methods
    private suspend fun insertStudentIntoFirestore(student: StudentTable) {
        withContext(Dispatchers.IO) {
            firestore.collection("students").document(student.id.toString()).set(student).await()
        }
    }

    private suspend fun updateStudentInFirestore(student: StudentTable) {
        withContext(Dispatchers.IO) {
            firestore.collection("students").document(student.id.toString()).set(student).await()
        }
    }

    private suspend fun deleteStudentFromFirestore(student: StudentTable) {
        withContext(Dispatchers.IO) {
            firestore.collection("students").document(student.id.toString()).delete().await()
        }
    }

    suspend fun getAllStudentsFromFirestore(): List<StudentTable> {
        return withContext(Dispatchers.IO) {
            val snapshot = firestore.collection("students").get().await()
            snapshot.toObjects(StudentTable::class.java)
        }
    }

    // Synchronize Room with Firestore
    suspend fun syncRoomWithFirestore() {
        val firestoreStudents = getAllStudentsFromFirestore()

        withContext(Dispatchers.IO) {
            val roomStudents = studentDao.getAllStudentsDirect() // Fetch data directly

            // Sync Room from Firestore
            firestoreStudents.forEach { firestoreStudent ->
                val roomStudent = roomStudents.find { it.id == firestoreStudent.id }
                if (roomStudent == null) {
                    studentDao.insertStudent(firestoreStudent) // Insert Firestore student into Room
                }
            }

            // Remove extra Room entries that are not in Firestore
            roomStudents.forEach { roomStudent ->
                val firestoreStudent = firestoreStudents.find { it.id == roomStudent.id }
                if (firestoreStudent == null) {
                    studentDao.delete(roomStudent) // Remove Room student not in Firestore
                }
            }
        }
    }
}