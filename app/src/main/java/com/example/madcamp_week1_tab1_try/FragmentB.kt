package com.example.madcamp_week1_tab1_try

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.madcamp_week1_tab1_try.databinding.FragmentBBinding
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentB : Fragment() {

    private lateinit var postAdapter: PostAdapter

    private lateinit var recyclerView: RecyclerView

    private lateinit var searchEditText: EditText

    private var selectedPost: PostAdapter.postItem? = null

    companion object {
        private const val pick_image_request = 1
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_b, container, false)

        val testButton: Button = view.findViewById(R.id.testBtn)
        testButton.setOnClickListener {
            navigateToTestFragment()
        }

        val postButton: Button = view.findViewById(R.id.postBtn)
        postButton.setOnClickListener {
            postDialog()
        }

        recyclerView = view.findViewById(R.id.postRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        postAdapter = PostAdapter(requireContext(), { position ->

            selectedPost = postAdapter.getItem(position)

            updateProfileDialog()
        })

        postAdapter.addItem("잃어버린 강이를 찾아주세요..","ㅠㅠㅠ 도와주세요.")

        postAdapter.setFilteredItemList()

        recyclerView.adapter = postAdapter

        searchEditText = view.findViewById(R.id.post_search_bar)

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
                // 필요한 경우 처리
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트가 변경될 때마다 호출됨
                val searchText = charSequence?.toString() ?: ""
                postAdapter.filter(searchText)
            }

            override fun afterTextChanged(editable: Editable?) {
                // EditText의 텍스트가 변경된 후에 호출됨
                // 필요한 경우 처리
            }
        })

        return view
    }

    private fun updateProfileDialog() {
        selectedPost?.let { profile ->
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.post_dialog)

            val title: TextView = dialog.findViewById(R.id.postTitleText)
            val text: TextView = dialog.findViewById(R.id.postText)

            title.text = profile.postTitle
            text.text = profile.postText


            val closeButton: Button = dialog.findViewById(R.id.btnClose)
            closeButton.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }
    }

    private fun postDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.post_text_dialog)

        val title: EditText = dialog.findViewById(R.id.postTitleEditText)
        val text: EditText = dialog.findViewById(R.id.postTextEditText)

        val closeButton: Button = dialog.findViewById(R.id.btnPostCancel)
        closeButton.setOnClickListener {
            dialog.dismiss()
        }

        val addButton: Button = dialog.findViewById(R.id.btnPostAdd)
        addButton.setOnClickListener {
            postAdapter.addItem(title.text.toString(), text.text.toString())
            postAdapter.setFilteredItemList()
            dialog.dismiss()
        }
        dialog.show()
    }


    private fun navigateToTestFragment() {
        val testFragment = TestFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_test, testFragment)
        transaction.addToBackStack(null)  // 선택 사항: 백 스택에 추가
        transaction.commit()
    }



}