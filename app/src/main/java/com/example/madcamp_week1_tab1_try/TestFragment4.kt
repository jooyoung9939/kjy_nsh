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

class TestFragment4 : Fragment() {

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

        fun newInstance(totalScore: Int): TestFragment4 {
            val fragment = TestFragment4()
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

        val rootView = inflater.inflate(R.layout.fragment_test4, container, false)
        initializeViews(rootView)
        setQuestionAndOptions("강아지에게 충분한 실내 및 외부 공간을 제공할 수 있나요?", "제공하기 어렵다", "좁지만 제공 가능하다", "충분한 공간이 제공 가능하다", "완벽히 넓은 공간이 제공 가능하다")

        nextButton.setOnClickListener {
            calculateScore()
            navigateToTestFragment4()
        }

        return rootView
    }

    private fun initializeViews(view: View) {
        questionTextView = view.findViewById(R.id.questionTextView4)
        answerRadioGroup = view.findViewById(R.id.answerRadioGroup4)
        answerOption1 = view.findViewById(R.id.answerOption41)
        answerOption2 = view.findViewById(R.id.answerOption42)
        answerOption3 = view.findViewById(R.id.answerOption43)
        answerOption4 = view.findViewById(R.id.answerOption44)
        nextButton = view.findViewById(R.id.nextButton4)
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

    private fun navigateToTestFragment4() {
        val testFragment5 = TestFragment5.newInstance(totalScore)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_test, testFragment5)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

