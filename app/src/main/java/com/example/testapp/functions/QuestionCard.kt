package com.example.testapp.functions

data class QuestionCard(
    val question: String,
    val options: List<String>,
    val correctOptions: List<String>
)