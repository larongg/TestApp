package com.example.testapp.ui.addquestions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.testapp.R
import com.example.testapp.functions.TestApp
import com.google.android.material.textfield.TextInputEditText

class AddQuestionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_questions, container, false)
        val testApp = TestApp()

        val button_add = view.findViewById<Button>(R.id.add)

        button_add.setOnClickListener {


        }
        return view
    }
}