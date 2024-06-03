package com.example.remindser.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
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

class SignUpActivity : AppCompatActivity() {

    lateinit var email_et: TextInputEditText
    lateinit var password_et: TextInputEditText
    lateinit var login_tv: TextView
    lateinit var confirm_password_et: TextInputEditText
    lateinit var btn_signup:AppCompatButton

    lateinit var fAuth:FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        email_et = findViewById(R.id.email_et)
        password_et = findViewById(R.id.password_et)
        login_tv = findViewById(R.id.login_tv)
        confirm_password_et = findViewById(R.id.confirm_password_et)
        btn_signup = findViewById(R.id.btn_signup)

        fAuth = FirebaseAuth.getInstance()

        btn_signup.setOnClickListener { signUp()}
        login_tv.setOnClickListener { startActivity(Intent(this,LoginActivity::class.java)) }

    }

    private fun signUp() {
        val email = email_et.text.toString()
        val password = password_et.text.toString()
        val confirm_password = confirm_password_et.text.toString()

        if(email.isEmpty() || password.isEmpty() || confirm_password.isEmpty()){
            Toast.makeText(this,"All fields are required",Toast.LENGTH_SHORT).show()
        }

        if(password != confirm_password){
            Toast.makeText(this,"Passwords do not match",Toast.LENGTH_SHORT).show()
        }

        fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this,"User Created",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,MainActivity::class.java))
            }else {
                it.exception?.let {
                    Log.e("SignUp", "Error: ${it.message}")
                }
            }
        }
    }
}