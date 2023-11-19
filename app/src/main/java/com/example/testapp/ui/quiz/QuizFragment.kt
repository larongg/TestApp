package com.example.testapp.ui.quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Space
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import com.example.testapp.R
import com.example.testapp.functions.TestApp
import com.example.testapp.ui.pager.QuestionActivity
import com.google.android.material.card.MaterialCardView
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textview.MaterialTextView
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
            // Создание CardView
            val cardView = MaterialCardView(requireContext())
            val cardParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )
            cardParams.setMargins(0, 0, 0, 16) // Опциональные отступы между карточками
            cardView.layoutParams = cardParams

            // Создание вложенного LinearLayout
            val linearLayout = LinearLayout(requireContext())
            linearLayout.orientation = LinearLayout.VERTICAL
            linearLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
            )
            linearLayout.setPadding(5)

            // TextView
            val textView = MaterialTextView(requireContext())
            val textParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f
            )
            textParams.leftMargin = 5
            textView.layoutParams = textParams
            textView.text = category
            textView.textSize = 24f
            textView.setTypeface(null, Typeface.BOLD)

            // Line
            val line = LinearLayout(requireContext())
            val lineParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f
            )
            lineParams.setMargins(10, 0, 10, 0)
            lineParams.height = 5
            line.layoutParams = lineParams
            line.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.main_color))

            // Special Functions LinearLayout
            val specialFunctionsLayout = LinearLayout(requireContext())
            specialFunctionsLayout.orientation = LinearLayout.HORIZONTAL
            specialFunctionsLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f
            )

            val checkBox = MaterialCheckBox(requireContext())
            checkBox.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 2f
            )
            checkBox.minHeight = 0
            checkBox.minWidth = 0

            val dropDownList = MaterialTextView(requireContext())
            dropDownList.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 2f
            )
            dropDownList.gravity = Gravity.CENTER
            dropDownList.text = "▼"
            dropDownList.textSize = 24f
            dropDownList.translationY = -2f

            val delCategory = ImageView(requireContext())
            delCategory.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 2f
            )
            delCategory.setImageResource(R.drawable.trash_can)
            delCategory.isClickable = true

            val pen = ImageView(requireContext())
            pen.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 2f
            )
            pen.setImageResource(R.drawable.edit_text)
            pen.isClickable = true

            val space = Space(requireContext())
            space.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1f
            )

            val run = MaterialTextView(requireContext())
            val runLayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                2f
            )
            runLayoutParams.rightMargin = 5
            run.layoutParams = runLayoutParams
            run.isClickable = true
            run.text = "▶"
            run.textSize = 24f
            run.gravity = Gravity.CENTER
            run.setOnClickListener {
                val intent = Intent(requireContext(), QuestionActivity::class.java)
                intent.putExtra("category", textView.text.toString())
                startActivity(intent)
            }

            specialFunctionsLayout.addView(checkBox)
            specialFunctionsLayout.addView(dropDownList)
            specialFunctionsLayout.addView(delCategory)
            specialFunctionsLayout.addView(pen)
            specialFunctionsLayout.addView(space)
            specialFunctionsLayout.addView(run)

            // Добавление элементов во вложенный LinearLayout
            linearLayout.addView(textView)
            linearLayout.addView(line)
            linearLayout.addView(specialFunctionsLayout)

            // Добавление вложенного LinearLayout в CardView
            cardView.addView(linearLayout)

            // Добавление CardView в родительский контейнер
            scrollContainer.addView(cardView)
        }

        return view
    }
}
