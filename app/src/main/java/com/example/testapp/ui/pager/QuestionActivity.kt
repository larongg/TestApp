package com.example.testapp.ui.pager

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.testapp.R
import com.example.testapp.functions.QuestionCard
import com.example.testapp.functions.TestApp
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class QuestionActivity : FragmentActivity() {

    private lateinit var adapter: NumberAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        val category = intent.getStringArrayListExtra("category")
        val testApp = TestApp()
        testApp.loadQuestions(filesDir.path)
        val categories = mutableListOf<String>()
        val questions = mutableListOf<QuestionCard>()

        category!!.forEach {categoryText ->
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
    }
}