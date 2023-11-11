package com.example.viktorina.data

import com.example.viktorina.R
import com.example.viktorina.module.QuizData
import java.util.Collections

class DataSource {
    private val list = mutableListOf<QuizData>()
    private var shuffleState = false

    fun getQuizData() : List<QuizData>{
        list.add(
            QuizData(
                "What are the main building blocks of Flutter UIs?',",
                "",
                0,
                answers = listOf(
                    "Widgets",
                    "Components",
                    "Blocks",
                    "Functions",
                )
            )
        )
        list.add(
            QuizData(
                "How are Flutter UIs built?",
                "",
                0,
                answers = listOf(
                    "By combining widgets in code",
                    "By combining widgets in a visual editor",
                    "By defining widgets in config files",
                    "By using XCode for iOS and Android Studio for Android"
                )
            ),
        )
        list.add(
            QuizData(
                "What\'s the purpose of a StatefulWidget?",
                "",
                0,
                answers = listOf(
                    "Update UI as data changes",
                    "Update data as UI changes",
                    "Ignore data changes",
                    "Render UI that does not depend on data"
                )
            )
        )
        list.add(
            QuizData(
                "",
                "Who is he?",
                R.drawable.img,
                answers = listOf(
                    "Ronoldo",
                    "Neymar",
                    "Messi",
                    "Jude Belingem"
                )
            )
        )

        list.add(
            QuizData(
                "",
                "Who is it?",
                R.drawable.img_1,
                answers = listOf(
                    "Rabbit",
                    "Cow",
                    "Sheep",
                    "Horse"
                )
            )
        )
        if (shuffleState) {
            list.shuffle()
        }
        return list
    }

    fun setShuffleState(state : Boolean){
        shuffleState = state
    }
}