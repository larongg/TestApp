package com.example.testapp.ui.pager

import android.content.Context
import android.widget.LinearLayout
import android.widget.RadioGroup
import com.example.testapp.R
import com.example.testapp.functions.dpToPx
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.radiobutton.MaterialRadioButton

class QuestionsView(context: Context) : LinearLayout(context) {
    private val radioList = mutableListOf<MaterialRadioButton>()
    private val checkList = mutableListOf<MaterialCheckBox>()

    init {
        orientation = VERTICAL
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        ).apply {
            topMargin = dpToPx(10f)
            setBackgroundResource(R.drawable.border)
        }
    }

    fun addRadioGroup(options: List<String>) {
        val radioGroup = RadioGroup(context)

        for (option in options) {
            val newRadioButton = MaterialRadioButton(context)
            newRadioButton.layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            )
            newRadioButton.text = option
            newRadioButton.textSize = 20f

            radioList.add(newRadioButton)
            radioGroup.addView(newRadioButton)
        }

        addView(radioGroup)
    }

    fun addCheckBoxGroup(options: List<String>) {
        for (option in options) {
            val newCheckBox = MaterialCheckBox(context)
            newCheckBox.layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            )
            newCheckBox.text = option
            newCheckBox.textSize = 20f

            checkList.add(newCheckBox)
            addView(newCheckBox)
        }
    }

    fun checkRadioButtons(correctOptions: List<String>) {
        radioList.forEach {
            it.isClickable = false
            if (correctOptions.contains(it.text)) {
                it.setBackgroundColor(resources.getColor(R.color.true_color, null))
            }
            else {it.setBackgroundColor(resources.getColor(R.color.false_color, null))}
        }
    }

    fun checkCheckBoxes(correctOptions: List<String>) {
        checkList.forEach {
            it.isClickable = false
            if (correctOptions.contains(it.text)) {
                it.setBackgroundColor(resources.getColor(R.color.true_color, null))
            }
            else {it.setBackgroundColor(resources.getColor(R.color.false_color, null))}
        }
    }

    fun checkRadioPressed():Boolean {
        radioList.forEach {
            if (it.isChecked) {
                return true
            }
        }
        return false
    }

    fun checkCheckBoxPressed():Boolean {
        checkList.forEach {
            if (it.isChecked) {
                return true
            }
        }
        return false
    }
}
