package com.example.testapp.ui.addquestions

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.testapp.R
import com.example.testapp.functions.TestApp
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

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

    private fun createNewTextInputLayout(): TextInputLayout {
        val newTextInputLayout = TextInputLayout(requireContext())
        newTextInputLayout.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            1f
        )

        return newTextInputLayout
    }

    private fun createNewEditText(): TextInputEditText {
        val newEditText = TextInputEditText(
            ContextThemeWrapper(requireContext(), R.style.MyTextInputEditText),
            null,
            0
        )
        newEditText.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        newEditText.textSize = 16f
        newEditText.id = View.generateViewId()
        newEditText.setBackgroundColor(resources.getColor(R.color.transparent, null))

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

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private lateinit var category: MaterialAutoCompleteTextView
    private lateinit var question: TextInputEditText
    private lateinit var option1: TextInputEditText
    private lateinit var check1: MaterialCheckBox

    private val layouts = mutableListOf<LinearLayout>()
    private val optionsMap = hashMapOf<Int, TextInputEditText>()
    private val checkMap = hashMapOf<Int, MaterialCheckBox>()

    private val stateOptions = "state_options"
    private val stateChecks = "state_checks"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_questions, container, false)
        val testApp = TestApp()

        val buttonAdd = view.findViewById<MaterialButton>(R.id.add)
        val buttonDel = view.findViewById<MaterialButton>(R.id.del_option)
        val buttonClear = view.findViewById<MaterialButton>(R.id.clear)
        val buttonAddOption = view.findViewById<MaterialButton>(R.id.add_option)

        val optionsLayout = view.findViewById<LinearLayout>(R.id.other_options)

        category = view.findViewById(R.id.category_input)
        question = view.findViewById(R.id.question_input)
        option1 = view.findViewById(R.id.option1_input)
        check1 = view.findViewById(R.id.checkBox1)

        optionsMap[option1.id] = option1
        checkMap[option1.id] = check1

        testApp.loadQuestions(requireContext().filesDir.path)
        val categories = testApp.getAllCategories().toTypedArray()
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, categories)
        category.setAdapter(adapter)

        if (savedInstanceState != null) {
            val savedOptions = savedInstanceState.getStringArrayList(stateOptions)
            val saveChecks = savedInstanceState.getStringArrayList(stateChecks)
            // Восстановление добавленных пользователем элементов
            for (key in savedOptions?.indices!!) {
                val newLinearLayout = createNewLinearLayout()
                val newTextInputLayout = createNewTextInputLayout()
                val newEditText = createNewEditText()
                val newCheckBox = createNewCheckBox()

                newEditText.setText(savedOptions[key])
                newCheckBox.isChecked = saveChecks?.get(key)?.toBoolean()!!
                newTextInputLayout.addView(newEditText)
                newLinearLayout.addView(newTextInputLayout)
                newLinearLayout.addView(newCheckBox)
                optionsLayout.addView(newLinearLayout)

                optionsMap[newEditText.id] = newEditText
                checkMap[newEditText.id] = newCheckBox
                layouts.add(newLinearLayout)
            }
        }

        // Создание нового варианта ответа
        buttonAddOption.setOnClickListener {
            val newLinearLayout = createNewLinearLayout()
            val newTextInputLayout = createNewTextInputLayout()
            val newEditText = createNewEditText()
            val newCheckBox = createNewCheckBox()

            newTextInputLayout.addView(newEditText)
            newLinearLayout.addView(newTextInputLayout)
            newLinearLayout.addView(newCheckBox)
            optionsLayout.addView(newLinearLayout)

            optionsMap[newEditText.id] = newEditText
            checkMap[newEditText.id] = newCheckBox
            layouts.add(newLinearLayout)
        }

        // Функция удаления последнего варианта ответа
        buttonDel.setOnClickListener {
            if (layouts.size != 0) {
                optionsLayout.removeView(layouts.last())
                layouts.removeLast()
            }
        }

        // Функция добавления карточки
        buttonAdd.setOnClickListener {
            val correctOptions = mutableListOf<String>()
            val optionsList = mutableListOf<String>()
            for (key in optionsMap.keys) {
                if (checkMap[key]!!.isChecked) {
                    correctOptions.add(optionsMap[key]?.text.toString())
                }
                optionsList.add(optionsMap[key]?.text.toString())
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
            optionsMap.clear()
            optionsMap[option1.id] = option1
            checkMap.clear()
            checkMap[option1.id] = check1
            optionsLayout.removeAllViews()
            category.editableText.clear()
            question.editableText.clear()
            option1.editableText.clear()

            Toast.makeText(requireContext(), "Очищено", Toast.LENGTH_SHORT).show()
        }
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Сохранение состояния для добавленных пользователем элементов
        val savedOptions = mutableListOf<String>()
        val savedCheck = mutableListOf<String>()
        for (key in optionsMap.keys) {
            if (key != option1.id) {
                savedOptions.add(optionsMap[key]?.text.toString())
                savedCheck.add(checkMap[key]?.isChecked.toString())
            }
        }
        outState.putStringArrayList(stateOptions, ArrayList(savedOptions))
        outState.putStringArrayList(stateChecks, ArrayList(savedCheck))
    }
}