package com.example.testapp.ui.quiz

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.testapp.R
import com.example.testapp.functions.TestApp
import java.io.IOException

class QuizFragment : Fragment() {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)
        val scrollContainer = view.findViewById<LinearLayout>(R.id.main_vertical_container)
        val testApp = TestApp()

        try {
            testApp.loadQuestions(requireContext().filesDir.path)
        } catch (_: IOException) {
            testApp.createQuestionsFile(requireContext().filesDir.path)
            testApp.loadQuestions(requireContext().filesDir.path)
        }

        for (category in testApp.getAllCategories().sorted()) {
            val newCardView = categoryCard(requireContext(), category, testApp.getAllQuestionsByKey(category).size)
            scrollContainer.addView(newCardView)
        }

        return view
    }
}
