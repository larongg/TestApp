package com.example.testapp.ui.quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.testapp.R
import com.example.testapp.functions.TestApp
import com.example.testapp.ui.pager.QuestionActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import java.io.IOException

class QuizFragment : Fragment() {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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
            val  newLinerLayout = LinearLayout(requireContext())
            newLinerLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            newLinerLayout.orientation = LinearLayout.HORIZONTAL

            val newButtonCategory = MaterialButton(requireContext())
            newButtonCategory.id = View.generateViewId()
            newButtonCategory.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1f)
            newButtonCategory.text = category
            newButtonCategory.setOnClickListener {
                val intent = Intent(requireContext(), QuestionActivity::class.java)
                startActivity(intent)
            }

            val newCheckBox = MaterialCheckBox(requireContext())
            newCheckBox.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                6f
            )

            newLinerLayout.addView(newButtonCategory)
            newLinerLayout.addView(newCheckBox)
            scrollContainer.addView(newLinerLayout)
        }


        return view
    }
}
