package com.example.testapp.ui.pager

import android.os.Bundle
import android.widget.Toolbar
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.testapp.R
import com.example.testapp.functions.TestApp

class QuestionActivity : FragmentActivity() {

    private lateinit var adapter: NumberAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        val category = intent.getStringExtra("category")
        val testApp = TestApp()
        testApp.loadQuestions(filesDir.path)
        val questions = testApp.getAllQuestionsByKey(category!!)

        adapter = NumberAdapter(this, category, questions)
        viewPager = findViewById(R.id.pager)
        viewPager.adapter = adapter
    }
}