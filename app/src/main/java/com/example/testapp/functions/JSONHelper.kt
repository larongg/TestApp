package com.example.testapp.functions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

const val FILE_NAME = "data.json"
const val DEFAULT_JSON_CONTENT = """{
    "Страны мира": [{
        "question": "Какая столица Франции?",
        "options": ["Лондон", "Мадрид", "Париж", "Берлин"],
        "correctOptions": ["Париж"]
    }],
    "Солнечная система": [{
        "question": "Сколько планет в Солнечной системе?",
        "options": ["6", "7", "8", "9"],
        "correctOptions": ["8"]
    }]}
    """
class JSONHelper {
    private val gson = Gson()
    private val listType = object : TypeToken<HashMap<String, MutableList<QuestionCard>>>() {}.type

    fun importFromJSON(path: String): HashMap<String, MutableList<QuestionCard>> {
        val file = File(path, FILE_NAME).readText()
        return gson.fromJson(file, listType)
    }

    fun createFile(path: String) {
        File(path, FILE_NAME).createNewFile()
        File(path, FILE_NAME).writeText(DEFAULT_JSON_CONTENT)
    }

    fun writeFile(category: String, card: QuestionCard, path: String) {
        val list = importFromJSON(path)

        if (list.containsKey(category) and !list[category]!!.contains(card)){
            list[category]!!.add(card)
        }
        if (!list.containsKey(category)) {
            list[category] = mutableListOf(card)
        }

        // Save the updated list to the file
        File(path, FILE_NAME).writeText(gson.toJson(list, listType))
    }
}
