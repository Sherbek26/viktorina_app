package com.example.viktorina

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.viktorina.data.DataSource
import com.example.viktorina.databinding.ActivityMainBinding
import com.example.viktorina.module.QuizData


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataSource = DataSource()
    private val listOfQuestions = arrayListOf<QuizData>()
    private var questionIndex = 0
    private var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataSource.setShuffleState(true)
        listOfQuestions.addAll(dataSource.getQuizData())
        setActiveQuestion(questionIndex)
        binding.submit.setOnClickListener {
            val checkedBtnId = binding.radioGroup.checkedRadioButtonId
            if (checkedBtnId == -1){
                Toast.makeText(this, "Please select any answer to keep it going!", Toast.LENGTH_SHORT).show()
            }
            val radioButton = findViewById<RadioButton>(checkedBtnId)
            val selectedAnswerText = radioButton.text
            if (selectedAnswerText == listOfQuestions[questionIndex].answers[0]) {
                binding.gainedScore.text = "Score: ${++score}"
            }

            if (questionIndex >= listOfQuestions.size-1) {
                binding.textQuestionNo.text = ""
                binding.gainedScore.text = ""
                val alertDialog = AlertDialog.Builder(this)
                    .setTitle("Game Over")
                    .setIcon(R.drawable.baseline_front_hand_24)
                    .setCancelable(false)
                    .setMessage("Thank you for finishing game successfully.You have scored $score out of ${questionIndex+1}")
                    .setPositiveButton("Restart") { _, _ ->
                        restart()
                    }
                    .setNegativeButton("Exit"){_, _ ->
                        finish()
                    }
                val dialog = alertDialog.create()
                dialog.show()
            }else {
                binding.radioGroup.clearCheck()
                setActiveQuestion(++questionIndex)
            }
        }

    }

    private fun restart() {
        questionIndex = 0
        score = 0
        binding.gainedScore.text = "Score: $score"
        binding.radioGroup.clearCheck()
        listOfQuestions.shuffle()
        setActiveQuestion(questionIndex)
    }

    private fun setActiveQuestion(index : Int){
        val currentQuestion = listOfQuestions[index]
        if (currentQuestion.textQuestion == "" && currentQuestion.imageQuestion != 0){
            binding.imageQuestion.visibility = View.VISIBLE
            binding.imageQuestionDescription.visibility = View.VISIBLE
            binding.textQuestion.visibility = View.INVISIBLE
            binding.imageQuestion.setImageResource(currentQuestion.imageQuestion)
            binding.imageQuestionDescription.text = currentQuestion.imageDescription
        }
        else{
            binding.textQuestion.visibility = View.VISIBLE
            binding.imageQuestion.visibility = View.INVISIBLE
            binding.imageQuestionDescription.visibility = View.INVISIBLE

            binding.textQuestion.text = currentQuestion.textQuestion
        }
        val answers = currentQuestion.answers.shuffled()
        binding.radioButton.text = answers[0]
        binding.radioButton2.text = answers[1]
        binding.radioButton3.text = answers[2]
        binding.radioButton4.text = answers[3]

        binding.textQuestionNo.text = "Question: ${index.inc()}"

    }
//    private fun timeControl(){
//        val chronometr = binding.timer
//        chronometr.stop()
//        chronometr.start()
//        chronometr.setOnChronometerTickListener {chronometr->
//            if (chronometr.text.toString()=="00:15"){
//                setActiveQuestion(++questionIndex)
//                chronometr.stop()
//                chronometr.start()
//            }
//        }
//    }
}