package com.example.madcamp_week1_tab1_try

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class TestFragment : Fragment() {

    private lateinit var questionTextView: TextView
    private lateinit var answerRadioGroup: RadioGroup
    private lateinit var answerOption1: RadioButton
    private lateinit var answerOption2: RadioButton
    private lateinit var answerOption3: RadioButton
    private lateinit var answerOption4: RadioButton
    private lateinit var nextButton: Button

    private var totalScore: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_test, container, false)
        initializeViews(rootView)
        setQuestionAndOptions("테스트 질문 1", "선택지 1", "선택지 2", "선택지 3", "선택지 4")

        nextButton.setOnClickListener {
            calculateScore()
            navigateToTestFragment()
        }

        return rootView
    }

    private fun initializeViews(view: View) {
        questionTextView = view.findViewById(R.id.questionTextView)
        answerRadioGroup = view.findViewById(R.id.answerRadioGroup)
        answerOption1 = view.findViewById(R.id.answerOption1)
        answerOption2 = view.findViewById(R.id.answerOption2)
        answerOption3 = view.findViewById(R.id.answerOption3)
        answerOption4 = view.findViewById(R.id.answerOption4)
        nextButton = view.findViewById(R.id.nextButton)
    }

    private fun setQuestionAndOptions(question: String, option1: String, option2: String, option3: String, option4: String) {
        questionTextView.text = question
        answerOption1.text = option1
        answerOption2.text = option2
        answerOption3.text = option3
        answerOption4.text = option4
    }

    private fun calculateScore() {
        val selectedRadioButtonId = answerRadioGroup.checkedRadioButtonId
        val selectedRadioButton: RadioButton? = view?.findViewById(selectedRadioButtonId)

        when (selectedRadioButton) {
            answerOption1 -> totalScore += 5
            answerOption2 -> totalScore += 10
            answerOption3 -> totalScore += 15
            answerOption4 -> totalScore += 20
        }
    }

    private fun navigateToTestFragment() {
        val testFragment2 = TestFragment2.newInstance(totalScore)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_test, testFragment2)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

