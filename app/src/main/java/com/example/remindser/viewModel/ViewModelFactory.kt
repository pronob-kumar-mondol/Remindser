package com.example.remindser.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.remindser.database.StudentDatabase
import com.example.remindser.repository.StudentRepository
import com.google.firebase.database.FirebaseDatabase

class ViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            val studentDao = StudentDatabase.getDatabaseInstance(application).studentDao()
            val firebaseDatabase = FirebaseDatabase.getInstance().reference.child("students")
            val repository = StudentRepository(studentDao, firebaseDatabase)
            return StudentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}