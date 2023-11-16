package com.example.testapp.ui.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.testapp.R

const val ARG_INT = "object"
const val ARG_CATEGORY = "category"
const val ARG_QUESTION = "question"
const val ARG_OPTIONS = "options"
const val ARG_CORRECT_OPTIONS = "correct_options"

class NumberFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_number, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_INT) && it.containsKey(ARG_CATEGORY) }?.apply {
            val textView = view.findViewById<TextView>(R.id.textView)
            val categoryOutput = view.findViewById<TextView>(R.id.category_output)
            val questionOutput = view.findViewById<TextView>(R.id.question_output)
            val optionsOutput = view.findViewById<TextView>(R.id.options_output)
            val correctOptionsOutput = view.findViewById<TextView>(R.id.correct_options_output)

            val number = getInt(ARG_INT)
            val category = getString(ARG_CATEGORY)
            val question = getString(ARG_QUESTION)
            val options = getStringArrayList(ARG_OPTIONS)
            val correctOptions = getStringArrayList(ARG_CORRECT_OPTIONS)
            textView.text = number.toString()
            categoryOutput.text = category
            questionOutput.text = question
            optionsOutput.text = options.toString()
            correctOptionsOutput.text = correctOptions.toString()
        }
    }
}