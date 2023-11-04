package com.example.testapp.functions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class JSONHelper {
    private val fileName = "data.json"
    private val gson = Gson()
    private val listType = object : TypeToken<List<HashMap<String, Any>>>() {}.type
    fun importFromJSON(path: String): List<HashMap<String, Any>> {
        val file = File(path, fileName).readText()
        return gson.fromJson(file, listType)
    }

    fun createFile(path: String) {
        val fileContents = """[
  {
    "category" : "Страны мира",
    "question": "Какая столица Франции?",
    "options": ["Лондон", "Мадрид", "Париж", "Берлин"],
    "correctOptions": ["Париж"]
  },
  {
    "category": "Солнечная система",
    "question": "Сколько планет в Солнечной системе?",
    "options": ["6", "7", "8", "9"],
    "correctOptions": ["8"]
  }
]"""
        File(path, fileName).createNewFile()
        File(path, fileName).writeText(fileContents)
    }

    fun writeFile(
        category: String,
        card: QuestionCard,
        path: String
    ) {
        val list = importFromJSON(path) as MutableList<HashMap<String, Any>>
        list.add(
            hashMapOf(
                "category" to category,
                "question" to card.question,
                "options" to card.options,
                "correctOptions" to card.correctOptions
            )
        )
        File(path, fileName).writeText(gson.toJson(list, listType))
    }
}
