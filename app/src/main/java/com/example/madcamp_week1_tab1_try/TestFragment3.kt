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

class TestFragment3 : Fragment() {

    private lateinit var questionTextView: TextView
    private lateinit var answerRadioGroup: RadioGroup
    private lateinit var answerOption1: RadioButton
    private lateinit var answerOption2: RadioButton
    private lateinit var answerOption3: RadioButton
    private lateinit var answerOption4: RadioButton
    private lateinit var nextButton: Button
    var totalScore = arguments?.getInt(ARG_TOTAL_SCORE, 0) ?: 0

    companion object {
        private const val ARG_TOTAL_SCORE = "totalScore"

        fun newInstance(totalScore: Int): TestFragment3 {
            val fragment = TestFragment3()
            val args = Bundle()
            args.putInt(ARG_TOTAL_SCORE, totalScore)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        totalScore = arguments?.getInt(ARG_TOTAL_SCORE, 0) ?: 0

        val rootView = inflater.inflate(R.layout.fragment_test3, container, false)
        initializeViews(rootView)
        setQuestionAndOptions("테스트 질문 3", "선택지 1", "선택지 2", "선택지 3", "선택지 4")

        nextButton.setOnClickListener {
            calculateScore()
            navigateToTestFragment3()
        }

        return rootView
    }

    private fun initializeViews(view: View) {
        questionTextView = view.findViewById(R.id.questionTextView3)
        answerRadioGroup = view.findViewById(R.id.answerRadioGroup3)
        answerOption1 = view.findViewById(R.id.answerOption31)
        answerOption2 = view.findViewById(R.id.answerOption32)
        answerOption3 = view.findViewById(R.id.answerOption33)
        answerOption4 = view.findViewById(R.id.answerOption34)
        nextButton = view.findViewById(R.id.nextButton3)
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

    private fun navigateToTestFragment3() {
        val testFragment4 = TestFragment4.newInstance(totalScore)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_test, testFragment4)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

