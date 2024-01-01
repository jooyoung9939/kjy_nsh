package com.example.madcamp_week1_tab1_try

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class ResultFragment : Fragment() {

    companion object {
        private const val ARG_TOTAL_SCORE = "totalScore"

        fun newInstance(totalScore: Int): ResultFragment {
            val fragment = ResultFragment()
            val args = Bundle()
            args.putInt(ARG_TOTAL_SCORE, totalScore)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var resultTextView: TextView
    private lateinit var scoreTextView: TextView
    private lateinit var backButton: Button
    var totalScore = arguments?.getInt(ARG_TOTAL_SCORE, 0) ?: 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View
        totalScore = arguments?.getInt(ARG_TOTAL_SCORE, 0) ?: 0
        if (totalScore >= 60) {
            rootView = inflater.inflate(R.layout.fragment_result_success, container, false)
        } else {
            rootView = inflater.inflate(R.layout.fragment_result_fault, container, false)
        }

        initializeViews(rootView)
        displayResult()
        setListeners()

        return rootView
    }

    private fun initializeViews(view: View) {
        resultTextView = view.findViewById(R.id.resultTextView)
        scoreTextView = view.findViewById(R.id.scoreTextView)
        backButton = view.findViewById(R.id.backButton)
    }

    private fun displayResult() {
        totalScore = arguments?.getInt(ARG_TOTAL_SCORE, 0) ?: 0

        resultTextView.text = "테스트 결과"
        scoreTextView.text = "점수: $totalScore"
    }

    private fun setListeners() {
        backButton.setOnClickListener {
            resetScoreAndNavigateToFragmentB()
        }
    }

    private fun resetScoreAndNavigateToFragmentB() {
        // 여기에서 점수 초기화 또는 필요한 작업 수행
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_test, FragmentB())
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

