package com.example.madcamp_week1_tab1_try

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class TestStartFragment: Fragment() {
    private lateinit var nextButton: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_test_start, container, false)
        initializeViews(rootView)

        nextButton.setOnClickListener {
            navigateToTestFragment()
        }

        return rootView
    }

    private fun initializeViews(view: View) {
        nextButton = view.findViewById(R.id.nextButton)
    }

    private fun navigateToTestFragment() {
        val testFragment = TestFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_test, testFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
