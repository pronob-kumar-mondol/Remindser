package com.example.remindser.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.remindser.R
import com.example.remindser.adapter.StudentAdapter
import com.example.remindser.viewModel.StudentViewModel
import com.example.remindser.viewModel.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var fab:FloatingActionButton
    lateinit var recyclerView:RecyclerView
    private lateinit var studentAdapter: StudentAdapter

    private val viewModel: StudentViewModel by viewModels{
        ViewModelFactory(application)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab=findViewById(R.id.fab_main)
        recyclerView=findViewById(R.id.recyclerView)

        studentAdapter=StudentAdapter(viewModel.allStudents){student->
            viewModel.deleteStudent(student)
        }
        recyclerView.adapter = studentAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.allStudents.observe(this, Observer {student->
            student?.let {
                studentAdapter.notifyDataSetChanged()
            }
        })


        fab.setOnClickListener {
            startActivity(Intent(this, AddStudent_Activity::class.java))
            finish()
        }



    }
}