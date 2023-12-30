package com.example.madcamp_week1_tab1_try

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

//
//class FragmentB : Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_b, container, false)
//    }
//
//}
class FragmentB : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn1 = view.findViewById<ImageView>(R.id.member_1)
        val btn2 = view.findViewById<ImageView>(R.id.member_2)
        val btn3 = view.findViewById<ImageView>(R.id.member_3)
        val btn4 = view.findViewById<ImageView>(R.id.member_4)
        val btn5 = view.findViewById<ImageView>(R.id.member_5)
        val btn6 = view.findViewById<ImageView>(R.id.member_6)
        val btn7 = view.findViewById<ImageView>(R.id.member_7)
        val btn8 = view.findViewById<ImageView>(R.id.member_8)
        val btn9 = view.findViewById<ImageView>(R.id.member_9)

        setClickListener(btn1, "1")
        setClickListener(btn2, "2")
        setClickListener(btn3, "3")
        setClickListener(btn4, "4")
        setClickListener(btn5, "5")
        setClickListener(btn6, "6")
        setClickListener(btn7, "7")
        setClickListener(btn8, "8")
        setClickListener(btn9, "9")
    }

    private fun setClickListener(imageView: ImageView, data: String) {
        imageView.setOnClickListener {
            val intent = Intent(requireContext(), ImageInsideActivity::class.java)
            intent.putExtra("data", data)
            startActivity(intent)
        }
    }
}
