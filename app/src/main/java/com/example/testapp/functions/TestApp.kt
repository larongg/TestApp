package com.example.testapp.functions

class TestApp {
    private val json = JSONHelper()
    private var shuffle = false
    private val questionCards = mutableMapOf<String, MutableList<QuestionCard>>()

    fun addQuestionCard(
        category: String,
        question: String,
        options: List<String>,
        correctOptions: List<String>,
        path: String
    ): String {
        val card = QuestionCard(question, options, correctOptions)

        when {
            questionCards.containsKey(category) -> {
                return if (questionCards[category]!!.contains(card)) {
                    "Такой вопрос уже существует"
                } else {
                    questionCards[category]!!.add(card)
                    json.writeFile(category, card, path)
                    "Вопрос добавлен"
                }
            }
        }

        questionCards[category] = mutableListOf(card)
        json.writeFile(category, card, path)
        return "Категория создана, вопрос добавлен"
    }

    fun loadQuestions(path: String) {
        questionCards.putAll(json.importFromJSON(path))
    }

    fun createQuestionsFile(path: String) {
        json.createFile(path)
    }

    fun getAllCategories(): MutableSet<String> {
        return questionCards.keys
    }

    fun getAllQuestionsByKey(key: String): MutableList<QuestionCard> {
        if (shuffle) {
            return questionCards[key]!!.toMutableList().apply { shuffle() }
        }
        return questionCards[key]!!
    }
}
