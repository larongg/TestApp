package com.example.testapp.ui.pager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.testapp.R
import com.example.testapp.functions.QuestionCard
import com.example.testapp.functions.TestApp
import com.example.testapp.ui.result.ActivityResult
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class QuestionActivity : FragmentActivity() {

    private lateinit var adapter: NumberAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var finishButton: Button
    private val categories = mutableListOf<String>()
    private val questions = mutableListOf<QuestionCard>()
    private val testApp = TestApp()
    private var startTimeMillis: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        val category = intent.getStringArrayListExtra("category")
        testApp.loadQuestions(filesDir.path)

        category!!.forEach { categoryText ->
            questions.addAll(testApp.getAllQuestionsByKey(categoryText))
            repeat(testApp.getAllQuestionsByKey(categoryText).size) {
                categories.add(categoryText)
            }
        }

        adapter = NumberAdapter(this, categories, questions)
        viewPager = findViewById(R.id.pager)
        viewPager.adapter = adapter

        tabLayout = findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = categories[position]
        }.attach()

        finishButton = findViewById(R.id.finish_button)
        finishButton.setOnClickListener { finishTest() }

        startTimeMillis = System.currentTimeMillis()
    }

    private fun finishTest() {
        val timeSpent = System.currentTimeMillis() - startTimeMillis
        val correctAnswers = (viewPager.adapter as NumberAdapter).getCorrectAnswersCount()
        val totalAnswers = (viewPager.adapter as NumberAdapter).getTotalAnswersCount()

        val resultIntent = Intent(this, ActivityResult::class.java).apply {
            putExtra("timeSpent", timeSpent)
            putExtra("correctAnswers", correctAnswers)
            putExtra("totalAnswers", totalAnswers)
            putExtra("totalQuestions", questions.size)
        }

        startActivity(resultIntent)

        finish()
    }
}