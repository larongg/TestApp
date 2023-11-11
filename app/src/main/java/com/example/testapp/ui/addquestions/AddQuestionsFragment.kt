package com.example.testapp.ui.addquestions

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.testapp.R
import com.example.testapp.functions.TestApp
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AddQuestionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_questions, container, false)
        val testApp = TestApp()

        val buttonAdd = view.findViewById<Button>(R.id.add)
        val buttonClear = view.findViewById<Button>(R.id.clear)
        val buttonAddOption = view.findViewById<Button>(R.id.add_option)
        val buttonAddCorrectOption = view.findViewById<Button>(R.id.add_correct_option)
        val listOptionButtons = mutableListOf<Button>()
        val listCorrectOptionButtons = mutableListOf<Button>()
        val othersOption = view.findViewById<LinearLayout>(R.id.others_options)

        buttonAddOption.setOnClickListener {
            // Создаем новый LinearLayout
            val newLinearLayout = LinearLayout(requireContext())
            newLinearLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            newLinearLayout.orientation = LinearLayout.HORIZONTAL

            // Создаем новый TextInputLayout
            val newTextInputLayout = TextInputLayout(requireContext())
            newTextInputLayout.layoutParams = LinearLayout.LayoutParams(
                300, // ваша ширина
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            // Создаем новый TextInputEditText
            val newEditText = TextInputEditText(requireContext())
            newEditText.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            newEditText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)


            // Добавляем TextInputEditText в TextInputLayout
            newTextInputLayout.addView(newEditText)

            // Создаем новую кнопку
            val newDelButton = Button(requireContext())
            newDelButton.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )


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

        buttonAdd.setOnClickListener { Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show() }

        buttonClear.setOnClickListener {}
        return view
    }

}