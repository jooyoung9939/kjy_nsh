package com.example.madcamp_week1_tab1_try

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

class ResultFragment : Fragment() {

    companion object {
        private const val pick_image_request = 1
        private const val ARG_TOTAL_SCORE = "totalScore"

        fun newInstance(totalScore: Int): ResultFragment {
            val fragment = ResultFragment()
            val args = Bundle()
            args.putInt(ARG_TOTAL_SCORE, totalScore)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: SharedViewModel

    private lateinit var resultTextView: TextView
    private lateinit var scoreTextView: TextView
    private lateinit var backButton: Button
    private lateinit var adoptButton: Button
    private lateinit var gobackbutton: Button
    var totalScore = arguments?.getInt(ARG_TOTAL_SCORE, 0) ?: 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View

        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        totalScore = arguments?.getInt(ARG_TOTAL_SCORE, 0) ?: 0
        if (totalScore >= 60) {
            rootView = inflater.inflate(R.layout.fragment_result_success, container, false)
            initializeViews(rootView)
            displayResult()
            setListeners()
        } else {
            rootView = inflater.inflate(R.layout.fragment_result_fault, container, false)
            initializeViews1(rootView)
            displayResult()
            setListeners1()
        }
        return rootView
    }

    private fun initializeViews(view: View) {
        resultTextView = view.findViewById(R.id.resultTextView)
        scoreTextView = view.findViewById(R.id.scoreTextView)
        adoptButton = view.findViewById(R.id.adoptButton)
        gobackbutton = view.findViewById(R.id.go_back_botton)
    }

    private fun initializeViews1(view: View) {
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
        adoptButton.setOnClickListener{
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.adopt_dialog)

            val name: EditText = dialog.findViewById(R.id.name1)
            val num: EditText = dialog.findViewById(R.id.num1)

            val gallery_btn: Button = dialog.findViewById(R.id.galleryBtn2)

            gallery_btn.setOnClickListener{
                openGallery()
                dialog.dismiss()
            }
            dialog.show()
        }
        gobackbutton.setOnClickListener {
            resetScoreAndNavigateToFragmentB()
        }
    }

    private fun openGallery() {
        val pickImageIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(pickImageIntent, pick_image_request)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == pick_image_request && resultCode == Activity.RESULT_OK) {
            val selectedImageUri: Uri? = data?.data

            val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.adopt_dialog, null)
            val dialog = Dialog(requireContext())
            dialog.setContentView(dialogView)

            val name: EditText = dialog.findViewById(R.id.name1)
            val num: EditText = dialog.findViewById(R.id.num1)
            val dogname: EditText = dialog.findViewById(R.id.name2)
            val centertag: EditText = dialog.findViewById(R.id.centertag)

            val adopt_button: Button = dialog.findViewById(R.id.btn_adopt)
            adopt_button.setOnClickListener{
                // 선택된 이미지 및 정보로 ViewModel 업데이트
                viewModel.setContactInfo(name.text.toString(), num.text.toString(), selectedImageUri, dogname.text.toString(), centertag.text.toString(), "안 잃어버림")
                dialog.dismiss()
            }

            val imageView: ImageView = dialog.findViewById(R.id.dogImage)
            viewModel.setSelectedImage(selectedImageUri)

            Glide.with(dialog.context)
                .load(selectedImageUri)
                .into(imageView)

            dialog.show()
        }
    }

    private fun setListeners1() {
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