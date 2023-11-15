package com.example.testapp.ui.addquestions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.testapp.R
import com.example.testapp.functions.TestApp
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText


class AddQuestionsFragment : Fragment() {
    private fun createNewLinearLayout(): LinearLayout {
        val newLinearLayout = LinearLayout(requireContext())
        newLinearLayout.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        newLinearLayout.orientation = LinearLayout.HORIZONTAL
        return newLinearLayout
    }

    /*fun createNewTextInputLayout(): TextInputLayout {
        val newTextInputLayout = TextInputLayout(requireContext())
        newTextInputLayout.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            1f
        )
        newTextInputLayout.boxStrokeWidth = 0
        newTextInputLayout.boxStrokeWidthFocused = 0
        return newTextInputLayout
    }*/

    private fun createNewEditText(hint : String = ""): TextInputEditText {
        val newEditText = TextInputEditText(requireContext())
        newEditText.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            1f
        )
        newEditText.hint = hint
        return newEditText
    }

    private fun createNewCheckBox(): MaterialCheckBox {
        val newCheckBox = MaterialCheckBox(requireContext())
        newCheckBox.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
            5f
        )
        newCheckBox.minHeight = 0
        newCheckBox.isChecked = false
        return newCheckBox
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_questions, container, false)
        val testApp = TestApp()

        val buttonAdd = view.findViewById<MaterialButton>(R.id.add)
        val buttonDel = view.findViewById<MaterialButton>(R.id.del_option)
        val buttonClear = view.findViewById<MaterialButton>(R.id.clear)
        val buttonAddOption = view.findViewById<MaterialButton>(R.id.add_option)

        val options = view.findViewById<LinearLayout>(R.id.options)
        val categoryLayout = view.findViewById<LinearLayout>(R.id.category)
        val category = createNewEditText("Категория")
        categoryLayout.addView(category)
        val questionLayout = view.findViewById<LinearLayout>(R.id.question)
        val question = createNewEditText("Вопрос")
        questionLayout.addView(question)

        val layouts = mutableListOf<LinearLayout>()
        val others = hashMapOf<Int, TextInputEditText>()
        val othersChecked = hashMapOf<Int, MaterialCheckBox>()
        val option1 = createNewEditText("Ответы")
        val check1 = createNewCheckBox()
        val linearLayout1 = createNewLinearLayout()
        linearLayout1.addView(option1)
        linearLayout1.addView(check1)
        options.addView(linearLayout1)
        others[option1.id] = option1
        othersChecked[option1.id] = check1

        fun showToast(message: String) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        // Создание нового варианта ответа
        buttonAddOption.setOnClickListener {
            val newLinearLayout = createNewLinearLayout()
            //val newTextInputLayout = createNewTextInputLayout()
            val newEditText = createNewEditText()
            val newCheckBox = createNewCheckBox()

            // Добавляем всё во всё
            //newTextInputLayout.addView(newEditText)
            //newLinearLayout.addView(newTextInputLayout)
            newLinearLayout.addView(newEditText)
            newLinearLayout.addView(newCheckBox)
            options.addView(newLinearLayout)

            // Добавляем созданные элементы в списки
            others[newEditText.id] = newEditText
            othersChecked[newEditText.id] = newCheckBox
            layouts.add(newLinearLayout)
        }

        // Функция удаления последнего варианта ответа
        buttonDel.setOnClickListener {
            if (layouts.size != 1) {
                options.removeView(layouts.last())
                layouts.removeLast()
            }
        }

        // Функция добавления карточки
        buttonAdd.setOnClickListener {
            val correctOptions = mutableListOf<String>()
            val optionsList = mutableListOf<String>()
            for (key in others.keys) {
                if (othersChecked[key]!!.isChecked) {
                    correctOptions.add(others[key]?.text.toString())
                }
                optionsList.add(others[key]?.text.toString())
            }
            optionsList.removeIf { it.isBlank() }
            correctOptions.removeIf { it.isBlank() }

            when {
                category.text.toString()
                    .isBlank() -> showToast("""Поле "Категория" пусто""")

                question.text.toString().isBlank() -> showToast("""Поле "Вопрос" пусто""")
                optionsList.isEmpty() -> showToast("""Поле "Варианты ответа" не заполнено""")
                !optionsList.any { it in correctOptions } -> showToast("Не выбраны правильные варианты ответа")
                else -> {
                    val added = testApp.addQuestionCard(
                        category.text.toString(),
                        question.text.toString(),
                        optionsList,
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
            others[option1.id] = option1
            othersChecked.clear()
            othersChecked[option1.id] = check1
            options.removeAllViews()
            options.addView(linearLayout1)
            category.editableText.clear()
            question.editableText.clear()
            option1.editableText.clear()

            Toast.makeText(requireContext(), "Очищено", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}