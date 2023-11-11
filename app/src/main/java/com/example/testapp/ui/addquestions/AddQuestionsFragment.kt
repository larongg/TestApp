package com.example.testapp.ui.addquestions

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.testapp.R
import com.example.testapp.functions.TestApp
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView


class AddQuestionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_questions, container, false)
        val testApp = TestApp()

        val buttonAdd = view.findViewById<Button>(R.id.add)
        val buttonClear = view.findViewById<Button>(R.id.clear)
        val buttonAddOption = view.findViewById<Button>(R.id.add_option)
        val buttonAddCorrectOption = view.findViewById<Button>(R.id.add_correct_option)
        val othersOption = view.findViewById<LinearLayout>(R.id.others_options)
        val othersCorrectOption = view.findViewById<LinearLayout>(R.id.others_correct_options)

        buttonAddOption.setOnClickListener {
            // Создаем новый LinearLayout
            val newLinearLayout = LinearLayout(requireContext())
            val params = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.topMargin = 10
            newLinearLayout.layoutParams = params
            newLinearLayout.orientation = LinearLayout.HORIZONTAL


            // Создаем новый TextInputLayout
            val newTextInputLayout = TextInputLayout(requireContext())
            newTextInputLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1f
            )
            newTextInputLayout.boxStrokeWidth = 2
            newTextInputLayout.boxStrokeWidthFocused = 2
            newTextInputLayout.boxStrokeColor = ContextCompat.getColor(
                requireContext(),
                R.color.purple_500
            )

            // Создаем новый TextInputEditText
            val newEditText = TextInputEditText(requireContext())
            newEditText.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            newEditText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            newEditText.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))


            // Добавляем TextInputEditText в TextInputLayout
            newTextInputLayout.addView(newEditText)

            // Создаем новую кнопку
            val newDelButton = MaterialButton(requireContext())
            newDelButton.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                4f
            )

            newDelButton.insetBottom = 0
            newDelButton.insetTop = 0
            newDelButton.text = getString(R.string.del_button)
            newDelButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 34f)

            // Добавляем TextInputLayout и кнопку в новый LinearLayout
            newLinearLayout.addView(newTextInputLayout)
            newDelButton.setOnClickListener {
                val parentLayout = newLinearLayout.parent as? ViewGroup
                parentLayout?.removeView(newLinearLayout)
            }
            newLinearLayout.addView(newDelButton)

            // Добавляем новый LinearLayout в родительский контейнер
            othersOption.addView(newLinearLayout)

        }

        buttonAddCorrectOption.setOnClickListener {
            // Создаем новый LinearLayout
            val newLinearLayout = LinearLayout(requireContext())
            val params = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.topMargin = 10
            newLinearLayout.layoutParams = params
            newLinearLayout.orientation = LinearLayout.HORIZONTAL


            // Создаем новый TextInputLayout
            val newTextInputLayout = TextInputLayout(requireContext())
            newTextInputLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1f
            )
            newTextInputLayout.boxStrokeWidth = 2
            newTextInputLayout.boxStrokeWidthFocused = 2
            newTextInputLayout.boxStrokeColor = ContextCompat.getColor(
                requireContext(),
                R.color.purple_500
            )


            // Создаем новый TextInputEditText
            val newEditText = TextInputEditText(requireContext())
            newEditText.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            newEditText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            newEditText.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))


            // Добавляем TextInputEditText в TextInputLayout
            newTextInputLayout.addView(newEditText)

            // Создаем новую кнопку
            val newDelButton = MaterialButton(requireContext())
            newDelButton.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                4f
            )

            newDelButton.insetBottom = 0
            newDelButton.insetTop = 0
            newDelButton.text = getString(R.string.del_button)
            newDelButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 34f)

            // Добавляем TextInputLayout и кнопку в новый LinearLayout
            newLinearLayout.addView(newTextInputLayout)
            newDelButton.setOnClickListener {
                val parentLayout = newLinearLayout.parent as? ViewGroup
                parentLayout?.removeView(newLinearLayout)
            }
            newLinearLayout.addView(newDelButton)

            // Добавляем новый LinearLayout в родительский контейнер
            othersCorrectOption.addView(newLinearLayout)
        }

        buttonAdd.setOnClickListener {
            Toast.makeText(
                context, "Вопрос добавлен", Toast.LENGTH_SHORT
            ).show()
        }

        buttonClear.setOnClickListener {
            othersOption.removeAllViews()
            othersCorrectOption.removeAllViews()
            val categoryInput = view.findViewById<TextInputLayout>(R.id.category_input)
            categoryInput.editText?.text?.clear()
            val questionsInput = view.findViewById<TextInputLayout>(R.id.question_input)
            questionsInput.editText?.text?.clear()
            val option1 = view.findViewById<TextInputLayout>(R.id.option1_input)
            option1.editText?.text?.clear()
            val correctOption1 = view.findViewById<TextInputLayout>(R.id.correct_option1_input)
            correctOption1.editText?.text?.clear()
            Toast.makeText(requireContext(), "Очищено", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}