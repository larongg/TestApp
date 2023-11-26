package com.example.testapp.ui.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.testapp.R

class ActivityResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val timeSpentTextView = findViewById<TextView>(R.id.time)
        val correctAnswersTextView = findViewById<TextView>(R.id.correct_answers)
        val totalAnswersTextView = findViewById<TextView>(R.id.total_answers)
        val totalQuestionsTextView = findViewById<TextView>(R.id.total_questions)
        val goHomeButton = findViewById<Button>(R.id.go_home_page)

        val timeSpent = intent.getLongExtra("timeSpent", 0)
        val correctAnswers = intent.getIntExtra("correctAnswers", 0)
        val totalAnswers = intent.getIntExtra("totalAnswers", 0)
        val totalQuestions = intent.getIntExtra("totalQuestions", 0)

        timeSpentTextView.text = getString(R.string.time_s, formatTime(timeSpent))
        correctAnswersTextView.text = getString(R.string.correct_answers_d, correctAnswers)
        totalAnswersTextView.text = getString(R.string.total_answers_d, totalAnswers)
        totalQuestionsTextView.text = getString(R.string.total_questions_d, totalQuestions)

        goHomeButton.setOnClickListener {
            finish()
        }
    }

    private fun formatTime(milliseconds: Long): String {
        val minutes = (milliseconds / 1000) / 60
        val seconds = (milliseconds / 1000) % 60
        return String.format(" %02d:%02d", minutes, seconds)
    }
}