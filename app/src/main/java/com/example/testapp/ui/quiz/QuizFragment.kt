package com.example.testapp.ui.quiz

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.Toast
import com.example.testapp.R
import com.example.testapp.functions.TestApp
import com.google.android.material.button.MaterialButton
import java.io.IOException

class QuizFragment : Fragment() {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)
        val parentLayout = view.findViewById<LinearLayout>(R.id.main_vertical_container)
        val testApp = TestApp()

        try {
            testApp.loadQuestions(requireContext().filesDir.path)
        } catch (_: IOException) {
            testApp.createQuestionsFile(requireContext().filesDir.path)
            testApp.loadQuestions(requireContext().filesDir.path)
        }

        for (category in testApp.getAllCategories()) {
            val linearLayout = LinearLayout(requireContext())
            linearLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            linearLayout.orientation = LinearLayout.VERTICAL

            @Suppress("NAME_SHADOWING")
            val container = LinearLayout(requireContext())
            container.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            container.orientation = LinearLayout.HORIZONTAL

            val button = MaterialButton(requireContext())
            button.id = View.generateViewId()
            button.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f)
            button.contentDescription = getString(R.string.app_name)
            button.text = category
            button.setOnClickListener {
                Toast.makeText(context, "Кнопка $category нажата", Toast.LENGTH_SHORT).show()
            }

            val switch = Switch(requireContext())
            switch.id = View.generateViewId()
            switch.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                6f
            )

            container.addView(button)
            container.addView(switch)
            linearLayout.addView(container)

            parentLayout.addView(linearLayout)
        }

        return view
    }
}
