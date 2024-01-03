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

class TestFragment2 : Fragment() {

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

        fun newInstance(totalScore: Int): TestFragment2 {
            val fragment = TestFragment2()
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

        val rootView = inflater.inflate(R.layout.fragment_test2, container, false)
        initializeViews(rootView)
        setQuestionAndOptions("일주일 중 집에 머무르는 날이 얼마나 되나요?", "1~2일", "3~4일", "5~6일", "매일")

        nextButton.setOnClickListener {
            calculateScore()
            navigateToTestFragment2()
        }

        return rootView
    }

    private fun initializeViews(view: View) {
        questionTextView = view.findViewById(R.id.questionTextView2)
        answerRadioGroup = view.findViewById(R.id.answerRadioGroup2)
        answerOption1 = view.findViewById(R.id.answerOption21)
        answerOption2 = view.findViewById(R.id.answerOption22)
        answerOption3 = view.findViewById(R.id.answerOption23)
        answerOption4 = view.findViewById(R.id.answerOption24)
        nextButton = view.findViewById(R.id.nextButton2)
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

    private fun navigateToTestFragment2() {
        val testFragment3 = TestFragment3.newInstance(totalScore)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_test, testFragment3)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

