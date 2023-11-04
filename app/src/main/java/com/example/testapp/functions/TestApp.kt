package com.example.testapp.functions

class TestApp {
    private val json = JSONHelper()
    private var shuffle = false
    private val questionCards = HashMap<String, MutableList<QuestionCard>>()

    fun addQuestionCard(
        category: String,
        question: String,
        options: List<String>,
        correctOptions: List<String>,
        path: String
    ): String {
        val card = QuestionCard(question, options, correctOptions)

        if (!questionCards.containsKey(category)) {
            questionCards.put(category, mutableListOf(card))
            json.writeFile(category, card, path)
            return "Категория создана, вопрос добавлен"
        } else if (!questionCards[category]!!.contains(card)) {
            questionCards[category]?.add(card)
            json.writeFile(category, card, path)
            return "Вопрос добавлен"
        }

        return "Такой вопрос уже существует"
    }

    fun loadQuestions(path: String) {
        for (map in json.importFromJSON(path)) {
            @Suppress("UNCHECKED_CAST")
            if (!questionCards.containsKey(map["category"])) {
                questionCards.put(
                    map["category"].toString(),
                    mutableListOf(
                        QuestionCard(
                            map["question"].toString(),
                            map["options"] as List<String>,
                            map["correctOptions"] as List<String>
                        )
                    )
                )
            } else {
                questionCards[map["category"]]!!.add(
                    QuestionCard(
                        map["question"].toString(),
                        map["options"] as List<String>,
                        map["correctOptions"] as List<String>
                    )
                )
            }
        }
    }

    fun createQuestionsFile(path: String) {
        json.createFile(path)
    }

    fun getAllCategories(): MutableSet<String> {
        return questionCards.keys
    }

    fun getAllQuestionsByKey(key: String): Any? {
        if (shuffle) {
            return questionCards[key]?.shuffle()
        }
        return questionCards[key]
    }
}
