package com.example.remindser.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.remindser.Entity.StudentTable
import com.example.remindser.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class StudentAdapter(private val onDeleteClick: (StudentTable) -> Unit):RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private var studentList = listOf<StudentTable>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentAdapter.StudentViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.student_card,parent,false)
        return StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StudentAdapter.StudentViewHolder, position: Int) {

        val currentStudent=studentList[position]
        holder.student_name.text=currentStudent?.studentName
        holder.student_number.text=currentStudent?.studentNumber

        // Calculate remaining time
        val remainingTime = calculateRemainingTime(currentStudent?.date, currentStudent?.time)
        holder.remaining_time.text = remainingTime

        // Call directly
        holder.call_dirrectly.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${currentStudent?.studentNumber}")
            }
            it.context.startActivity(intent)
        }

        // Delete student
        holder.delete.setOnClickListener {
            currentStudent?.let { student ->
                onDeleteClick(student)
            }
        }
    }


    override fun getItemCount(): Int {
        return studentList.size
    }

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val student_name: TextView= itemView.findViewById(R.id.student_name)
        val student_number: TextView=itemView.findViewById(R.id.student_number)
        val remaining_time: TextView= itemView.findViewById(R.id.student_remaining_time)
        val call_dirrectly: ImageView= itemView.findViewById(R.id.call_dirrectly)
        val delete: ImageView= itemView.findViewById(R.id.delete)
    }

    private fun calculateRemainingTime(date: String?, time: String?): CharSequence? {
        val dateTime = "$date $time"
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

        return try {
            val futureDate = dateFormat.parse(dateTime)
            val currentDate = Date()

            if (futureDate != null) {
                val diffInMillis = futureDate.time - currentDate.time
                val days = TimeUnit.MILLISECONDS.toDays(diffInMillis)
                val hours = TimeUnit.MILLISECONDS.toHours(diffInMillis) % 24
                val minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis) % 60

                "$days days, $hours hours, $minutes minutes remaining"
            } else {
                "Invalid date/time"
            }
        } catch (e: Exception) {
            "Invalid date/time"
        }
    }

    // Update the data in the adapter
    fun setStudents(students: List<StudentTable>) {
        studentList = students
        notifyDataSetChanged()
    }
}