package com.example.remindser.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.remindser.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var email_et: TextInputEditText
    lateinit var password_et: TextInputEditText
    lateinit var signUp_tv: TextView
    lateinit var btn_login: AppCompatButton
    lateinit var fAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        email_et = findViewById(R.id.email_et)
        password_et = findViewById(R.id.password_et)
        btn_login = findViewById(R.id.btn_login)
        signUp_tv = findViewById(R.id.signUp_tv)
        fAuth = FirebaseAuth.getInstance()
        val currentUser=fAuth.currentUser

        if (currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }


        btn_login.setOnClickListener { login() }


        signUp_tv.setOnClickListener { startActivity(Intent(this,SignUpActivity::class.java)) }



    }

    private fun login() {
        val email = email_et.text.toString()
        val password = password_et.text.toString()

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"All fields are required", Toast.LENGTH_SHORT).show()
        }

        fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this,"Login Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,MainActivity::class.java))
            }
        }
    }
}