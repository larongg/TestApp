package com.example.testapp.ui.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.testapp.R
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.radiobutton.MaterialRadioButton

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
            val categoryOutput = view.findViewById<TextView>(R.id.category_output)
            val questionOutput = view.findViewById<TextView>(R.id.question_output)
            val checkButton = view.findViewById<Button>(R.id.check_button)
            val layout = view.findViewById<LinearLayout>(R.id.scroll_question_layout)

            val category = getString(ARG_CATEGORY)
            val question = getString(ARG_QUESTION)
            val options = getStringArrayList(ARG_OPTIONS)
            val correctOptions = getStringArrayList(ARG_CORRECT_OPTIONS)
            categoryOutput.text = category
            questionOutput.text = question

            if (correctOptions!!.size == 1) {
                val radioGroup = RadioGroup(requireContext())
                val radioList = mutableMapOf<Int, MaterialRadioButton>()
                val linerLayout = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                linerLayout.topMargin = 10

                for (i in options!!.indices) {
                    val newRadioButton = MaterialRadioButton(requireContext())
                    newRadioButton.layoutParams = linerLayout
                    newRadioButton.setBackgroundResource(R.drawable.border)
                    newRadioButton.id = View.generateViewId()
                    newRadioButton.text = options[i]
                    radioList[newRadioButton.id] = newRadioButton
                    radioGroup.addView(newRadioButton)
                }

                layout.addView(radioGroup)

                checkButton.setOnClickListener {
                    radioGroup.isClickable = false

                    for (id in radioList.keys) {
                        if (radioList[id]?.text in correctOptions) {
                            radioList[id]?.setTextColor(
                                resources.getColor(
                                    R.color.true_color,
                                    null
                                )
                            )
                        } else {
                            radioList[id]?.setTextColor(
                                resources.getColor(
                                    R.color.false_color,
                                    null
                                )
                            )
                        }
                    }

                }
            } else {
                val checkList = mutableMapOf<Int, MaterialCheckBox>()
                val linerLayout = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                linerLayout.topMargin = 10

                for (i in options!!.indices) {
                    val newCheckBox = MaterialCheckBox(requireContext())
                    newCheckBox.layoutParams = linerLayout
                    newCheckBox.setBackgroundResource(R.drawable.border)
                    newCheckBox.id = View.generateViewId()
                    newCheckBox.text = options[i]
                    checkList[newCheckBox.id] = newCheckBox
                    layout.addView(newCheckBox)
                }

                checkButton.setOnClickListener {
                    for (id in checkList.keys) {
                        checkList[id]?.isClickable = false

                        if (checkList[id]?.text in correctOptions) {
                            checkList[id]?.setTextColor(
                                resources.getColor(
                                    R.color.true_color,
                                    null
                                )
                            )
                        } else {
                            checkList[id]?.setTextColor(
                                resources.getColor(
                                    R.color.false_color,
                                    null
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
