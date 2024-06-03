package com.example.remindser.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remindser.Entity.StudentTable
import com.example.remindser.database.StudentDatabase
import com.example.remindser.repository.StudentRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class StudentViewModel(application: Application):AndroidViewModel(application) {

    private val repository: StudentRepository
    val allStudents: LiveData<List<StudentTable>>

    init {
        val studentDao = StudentDatabase.getDatabaseInstance(application).studentDao()
        val firestore = FirebaseFirestore.getInstance()
        repository = StudentRepository(studentDao, firestore)
        allStudents = repository.getAllStudentsFromRoom()

        // Synchronize Room with Firestore on ViewModel initialization
        viewModelScope.launch {
            repository.syncRoomWithFirestore()
        }
    }

    fun insertStudent(student: StudentTable) = viewModelScope.launch {
        repository.insertStudent(student)
    }

    fun deleteStudent(student: StudentTable) = viewModelScope.launch {
        repository.deleteStudent(student)
    }

    fun updateStudent(student: StudentTable) = viewModelScope.launch {
        repository.updateStudent(student)
    }

    fun deleteById(id: Int) = viewModelScope.launch {
        repository.deleteStudentById(id)
    }


}