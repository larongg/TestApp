package com.example.testapp.ui.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.testapp.R

const val ARG_CATEGORY = "category"
const val ARG_QUESTION = "question"
const val ARG_OPTIONS = "options"
const val ARG_CORRECT_OPTIONS = "correct_options"

class NumberFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_number, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf {
            it.containsKey(ARG_CATEGORY) &&
                    it.containsKey(ARG_QUESTION) &&
                    it.containsKey(ARG_OPTIONS) &&
                    it.containsKey(ARG_CORRECT_OPTIONS)
        }?.apply {
            val questionOutput = view.findViewById<TextView>(R.id.question_output)
            val checkButton = view.findViewById<Button>(R.id.check_button)
            val layout = view.findViewById<LinearLayout>(R.id.scroll_question_layout)

            val question = getString(ARG_QUESTION)
            val options = getStringArrayList(ARG_OPTIONS)
            val correctOptions = getStringArrayList(ARG_CORRECT_OPTIONS)
            questionOutput.text = question

            val questionsView = QuestionsView(requireContext())

            if (correctOptions!!.size == 1) {
                questionsView.addRadioGroup(options!!.toList())
            } else {
                questionsView.addCheckBoxGroup(options!!.toList())
            }

            layout.addView(questionsView)

            checkButton.setOnClickListener {
                if (correctOptions.size == 1 && questionsView.checkRadioPressed()) {
                    questionsView.checkRadioButtons(correctOptions)
                } else if (correctOptions.size > 1 && questionsView.checkCheckBoxPressed()) {
                    questionsView.checkCheckBoxes(correctOptions)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Не выбраны варианты ответа",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
