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
        val category = view.findViewById<TextInputLayout>(R.id.category_input)
        val question = view.findViewById<TextInputLayout>(R.id.question_input)

        val others = hashMapOf<Int, TextInputLayout>()
        val option1Input = view.findViewById<TextInputLayout>(R.id.option1_input)
        others[option1Input.id] = option1Input

        val correctOthers = hashMapOf<Int, TextInputLayout>()
        val correctOption1Input = view.findViewById<TextInputLayout>(R.id.correct_option1_input)
        correctOthers[correctOption1Input.id] = correctOption1Input

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
            newTextInputLayout.id = View.generateViewId()

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
            newEditText.isSingleLine = false
            newEditText.maxLines = Int.MAX_VALUE


            // Добавляем TextInputEditText в TextInputLayout
            newTextInputLayout.addView(newEditText)
            others[newTextInputLayout.id] = newTextInputLayout

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
                others.remove(newTextInputLayout.id)
                val parentLayout = newLinearLayout.parent as? ViewGroup
                parentLayout?.removeView(newLinearLayout)
            }
            newLinearLayout.addView(newDelButton)

            // Добавляем новый LinearLayout в родительский контейнер
            othersOption.addView(newLinearLayout)

        }

        // Функция создания нового верного варианта ответа
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

            // Добавляем TextInputEditText в TextInputLayout
            newTextInputLayout.addView(newEditText)
            correctOthers[newTextInputLayout.id] = newTextInputLayout

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
                correctOthers.remove(newTextInputLayout.id)
                val parentLayout = newLinearLayout.parent as? ViewGroup
                parentLayout?.removeView(newLinearLayout)
            }
            newLinearLayout.addView(newDelButton)

            // Добавляем новый LinearLayout в родительский контейнер
            othersCorrectOption.addView(newLinearLayout)
        }

        // Функция добавления карточки
        buttonAdd.setOnClickListener {
            val correctOptions = mutableListOf<String>()
            for (key in correctOthers.keys) {
                correctOptions.add(correctOthers[key]?.editText?.text.toString())
            }
            correctOptions.removeIf { it.isBlank() }

            val options = mutableListOf<String>()
            for (key in others.keys) {
                options.add(others[key]?.editText?.text.toString())
            }
            options.removeIf { it.isBlank() }

            when {
                category.editText?.text.toString().isBlank() -> showToast("""Поле "Категория" пусто""")
                question.editText?.text.toString().isBlank() -> showToast("""Поле "Вопрос" пусто""")
                options.isEmpty() -> showToast("""Поле "Варианты ответа" не заполнено""")
                correctOptions.isEmpty() -> showToast("""Поле "Правильные варианты ответа" не заполнено""")
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
            correctOthers.clear()
            correctOthers[correctOption1Input.id] = correctOption1Input
            othersOption.removeAllViews()
            othersCorrectOption.removeAllViews()
            category.editText?.text?.clear()
            question.editText?.text?.clear()
            option1Input.editText?.text?.clear()
            correctOption1Input.editText?.text?.clear()

            Toast.makeText(requireContext(), "Очищено", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}