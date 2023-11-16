package com.example.testapp.ui.pager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testapp.functions.QuestionCard

class NumberAdapter(
    fragment: FragmentActivity,
    private val category: String,
    private val questions: MutableList<QuestionCard>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = questions.size

    override fun createFragment(position: Int): Fragment {
        val fragment = NumberFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_INT, position)
            putString(ARG_CATEGORY, category)
            putString(ARG_QUESTION, questions[position].question)
            putStringArrayList(ARG_OPTIONS, questions[position].options as ArrayList)
            putStringArrayList(ARG_CORRECT_OPTIONS, questions[position].correctOptions as ArrayList)
        }
        return fragment
    }
}