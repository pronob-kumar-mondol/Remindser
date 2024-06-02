package com.example.remindser.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.remindser.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var fab:FloatingActionButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab=findViewById(R.id.fab_main)


        fab.setOnClickListener {
            startActivity(Intent(this, AddStudent_Activity::class.java))
            finish()
        }



    }
}