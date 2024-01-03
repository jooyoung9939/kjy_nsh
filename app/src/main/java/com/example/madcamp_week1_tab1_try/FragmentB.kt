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

        postAdapter.addItem("우리 강아지, 귀여움 지수 폭발! 함께 나누는 특별한 순간","안녕하세요, 강아지 팬 여러분! 저희 가족의 작은 멤버인 강아지가 정말 귀여워서, 여러분과 함께 나누고 싶은 특별한 순간이 많아졌어요. 강아지와의 소중한 경험, 귀엽고 재미있는 에피소드, 혹은 궁금한 사항이 있다면 나눠주세요! 우리 함께 강아지 매력에 빠져봐요.")
        postAdapter.addItem("강아지 훈련 비법 공유! 함께 더 행복한 동거를 위해","안녕하세요, 강아지 키우는 분들! 강아지 훈련에 대한 효과적인 비법이나 경험을 공유하고 싶어서 글을 올려봅니다. 우리 함께 강아지들과의 즐거운 동거를 더 행복하게 만들어보세요. 어려운 문제, 성공적인 경험, 모두 자유롭게 나눠주세요!")
        postAdapter.addItem("강아지 용품 추천! 우리 강아지에게 최고의 케어 주는 방법은?","여러분 안녕하세요! 강아지를 키우며 발견한 유용한 용품이나 케어 제품에 대해 이야기하려고 합니다. 어떤 간식이나 장난감, 건강용품 등 강아지를 위한 최고의 제품은 무엇인지 함께 나눠보아요. 서로의 정보를 나누면서 더 나은 강아지 케어의 길을 찾아가요!")
        postAdapter.addItem("강아지와 함께 하는 야외 어드벤처! 추억 가득한 순간들","안녕하세요, 강아지와 함께 하는 야외 어드벤처에 관한 이야기를 나누려고 해요! 우리 강아지들과의 등산, 산책, 혹은 특별한 여행 경험에 대해 공유해 주세요. 어떤 장소에서 강아지와 함께하는 순간이 가장 특별했나요? 함께 즐거운 추억을 만들어보아요!")
        postAdapter.addItem("강아지 이름 고민 중! 여러분의 창의적인 제안 부탁드려요.","안녕하세요, 강아지 이름을 고민 중인데 여러분의 도움이 필요해요! 강아지의 특징이나 성격을 고려하여 창의적이고 사랑스러운 이름을 제안해 주세요. 강아지에게 딱 맞는 이름을 찾는 과정에서 여러분과 함께 나누고 싶어졌어요. 제안 부탁드려요!")
        postAdapter.addItem("강아지와 함께하는 특별한 레시피 공유! 건강하고 맛있게 먹이는 방법은??","안녕하세요, 강아지와 함께하는 특별한 레시피에 대해 이야기하려고 합니다! 집에서 만들어서 강아지에게 먹이는 맛있는 간식이나 식사 아이디어를 공유해 주세요. 건강에 좋고 강아지가 정말 좋아하는 레시피가 있다면 함께 나눠보아요. 맛과 영양, 둘 다 챙길 수 있는 레시피를 찾아 나눠보아요!")


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
        val testStartFragment = TestStartFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_test, testStartFragment)
        transaction.addToBackStack(null)  // 선택 사항: 백 스택에 추가
        transaction.commit()
    }



}