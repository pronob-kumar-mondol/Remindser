package com.example.remindser.Activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.example.remindser.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class AddStudent_Activity : AppCompatActivity() {

    lateinit var student_name: TextInputEditText
    lateinit var student_number: TextInputEditText
    lateinit var student_details: TextInputEditText
    lateinit var apoinment_date: AppCompatTextView
    lateinit var apoinment_time: AppCompatTextView
    lateinit var fab_date: FloatingActionButton
    lateinit var fab_time: FloatingActionButton
    lateinit var save_btn: AppCompatButton

    private var appointmentDate: String = ""
    private var appointmentTime: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        student_name=findViewById(R.id.student_name_et)
        student_number=findViewById(R.id.student_number_et)
        student_details=findViewById(R.id.student_details_et)
        apoinment_date=findViewById(R.id.apoinmentDateTV)
        apoinment_time=findViewById(R.id.apoinmentTimeTV)
        fab_date=findViewById(R.id.floatingActionButton2)
        fab_time=findViewById(R.id.fab_2)
        save_btn=findViewById(R.id.btn_save)


        fab_date.setOnClickListener { showDatePicker() }
        fab_time.setOnClickListener { showTimePicker() }

    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                appointmentTime = "$selectedHour:$selectedMinute"
                apoinment_time.text = appointmentTime
            },
            hour, minute, true
        )
        timePickerDialog.show()
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                appointmentDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                apoinment_date.text = appointmentDate
            },
            year, month, day
        )
        datePickerDialog.show()
    }
}