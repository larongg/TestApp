package com.example.testapp.ui.addquestions

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.testapp.R
import com.example.testapp.functions.TestApp
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class AddQuestionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_questions, container, false)
        val testApp = TestApp()

        val buttonAdd = view.findViewById<MaterialButton>(R.id.add)
        val buttonDel = view.findViewById<MaterialButton>(R.id.del_option)
        val buttonClear = view.findViewById<MaterialButton>(R.id.clear)
        val buttonAddOption = view.findViewById<MaterialButton>(R.id.add_option)
        val otherOptions = view.findViewById<LinearLayout>(R.id.others_options)
        val category = view.findViewById<TextInputLayout>(R.id.category_input)
        val question = view.findViewById<TextInputLayout>(R.id.question_input)

        val layouts = mutableListOf<LinearLayout>()
        val others = hashMapOf<Int, TextInputLayout>()
        val othersChecked = hashMapOf<Int, MaterialCheckBox>()
        val option1Input = view.findViewById<TextInputLayout>(R.id.option1_input)
        val check1 = view.findViewById<MaterialCheckBox>(R.id.option_1_check)
        others[option1Input.id] = option1Input
        othersChecked[option1Input.id] = check1


        // Функция для показа Toast
        fun showToast(message: String) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }


        // Функция создание нового варианта ответа
        buttonAddOption.setOnClickListener {
            // Создаем новый LinearLayout
            val newLinearLayout = LinearLayout(requireContext())
            val params = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
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
            newTextInputLayout.minimumHeight = 48
            newTextInputLayout.boxStrokeWidth = 2
            newTextInputLayout.boxStrokeWidthFocused = 3
            newTextInputLayout.boxStrokeColor = ContextCompat.getColor(
                requireContext(),
                R.color.purple_500
            )
            newTextInputLayout.id = View.generateViewId()


            // Создаем новый TextInputEditText
            val newEditText = TextInputEditText(requireContext())
            newEditText.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            newEditText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            newEditText.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            newEditText.isSingleLine = false
            newEditText.maxLines = Int.MAX_VALUE


            // Создаем новый CheckBox
            val newCheckBox = MaterialCheckBox(requireContext())
            newCheckBox.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                5f
            )
            newCheckBox.isChecked = false


            // Добавляем всё во всё
            newTextInputLayout.addView(newEditText)
            newLinearLayout.addView(newTextInputLayout)
            newLinearLayout.addView(newCheckBox)
            otherOptions.addView(newLinearLayout)
            others[newTextInputLayout.id] = newTextInputLayout
            othersChecked[newTextInputLayout.id] = newCheckBox
            layouts.add(newLinearLayout)
        }


        // Функция удаления последнего варианта ответа
        buttonDel.setOnClickListener {
            if (layouts.isNotEmpty()) {
                otherOptions.removeView(layouts.last())
                layouts.removeLast()
            }
        }


        // Функция добавления карточки
        buttonAdd.setOnClickListener {
            val correctOptions = mutableListOf<String>()
            val options = mutableListOf<String>()
            for (key in others.keys) {
                if (othersChecked[key]!!.isChecked) {
                    correctOptions.add(others[key]?.editText?.text.toString())
                }
                options.add(others[key]?.editText?.text.toString())
            }
            options.removeIf { it.isBlank() }
            correctOptions.removeIf { it.isBlank() }

            when {
                category.editText?.text.toString()
                    .isBlank() -> showToast("""Поле "Категория" пусто""")

                question.editText?.text.toString().isBlank() -> showToast("""Поле "Вопрос" пусто""")
                options.isEmpty() -> showToast("""Поле "Варианты ответа" не заполнено""")
                !options.any { it in correctOptions } -> showToast("Не выбраны правильные варианты ответа")
                else -> {
                    val added = testApp.addQuestionCard(
                        category.editText?.text.toString(),
                        question.editText?.text.toString(),
                        options,
                        correctOptions,
                        requireContext().filesDir.path
                    )
                    showToast(added)
                }
            }
        }


        // Функция очистки бланка
        buttonClear.setOnClickListener {
            others.clear()
            others[option1Input.id] = option1Input
            othersChecked.clear()
            othersChecked[option1Input.id] = check1
            otherOptions.removeAllViews()
            category.editText?.text?.clear()
            question.editText?.text?.clear()
            option1Input.editText?.text?.clear()

            Toast.makeText(requireContext(), "Очищено", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}