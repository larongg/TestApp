package com.example.testapp.ui.quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.testapp.R
import com.example.testapp.functions.TestApp
import com.example.testapp.ui.pager.QuestionActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.IOException

class QuizFragment : Fragment() {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)
        val scrollContainer = view.findViewById<LinearLayout>(R.id.main_vertical_container)
        val startButton = view.findViewById<FloatingActionButton>(R.id.start)
        val testApp = TestApp()
        val cards = mutableMapOf<String, CategoryCard>()

        try {
            testApp.loadQuestions(requireContext().filesDir.path)
        } catch (_: IOException) {
            testApp.createQuestionsFile(requireContext().filesDir.path)
            testApp.loadQuestions(requireContext().filesDir.path)
        }

        for (category in testApp.getAllCategories().sorted()) {
            val newCardView = CategoryCard(
                requireContext(),
                category,
                testApp.getAllQuestionsByKey(category).size
            )
            cards[category] = newCardView
            scrollContainer.addView(newCardView)
        }

        startButton.setOnClickListener {
            val chosenCards = mutableListOf<String>()
            cards.keys.forEach {
                if (cards[it]!!.getChecked()) {
                    chosenCards.add(it)
                }
            }
            if (chosenCards.isNotEmpty()) {
                val intent = Intent(requireContext(), QuestionActivity::class.java)
                intent.putStringArrayListExtra("category", ArrayList(chosenCards))
                requireContext().startActivity(intent)
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.noneChoosed),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return view
    }
}
