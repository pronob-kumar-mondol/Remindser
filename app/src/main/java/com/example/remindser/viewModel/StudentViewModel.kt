package com.example.remindser.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remindser.Entity.StudentTable
import com.example.remindser.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(
    private val repository: StudentRepository
): ViewModel() {

    val allStudents: LiveData<List<StudentTable>> = repository.getAllStudentsFromRoom()

    fun insertStudent(student: StudentTable) = viewModelScope.launch {
        repository.insertStudent(student)
    }

    fun deleteStudent(student: StudentTable) = viewModelScope.launch {
        repository.deleteStudent(student)
    }



}