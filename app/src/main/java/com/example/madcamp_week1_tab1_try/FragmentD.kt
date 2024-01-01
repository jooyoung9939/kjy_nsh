package com.example.madcamp_week1_tab1_try

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentD : Fragment() {

    private lateinit var customAdapter: CustomAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var viewModel: SharedViewModel

    private var selectedProfile: CustomAdapter.Item? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_d, container, false)
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        customAdapter = CustomAdapter(requireContext(), { position ->
            customAdapter.removeItem(position)
        }, { position ->
            // 클릭 시 선택된 프로필 저장
            selectedProfile = customAdapter.getItem(position)
            // 다이얼로그 업데이트
            updateProfileDialog()
        })

        customAdapter.addItem("조유리", "010-1100-0000")
        customAdapter.addItem("박재범", "010-8292-5237")
        customAdapter.addItem("김채원", "010-8729-7897")

        customAdapter.setFilteredItemList(customAdapter.itemList)

        recyclerView.adapter = customAdapter

        searchEditText = view.findViewById(R.id.search_bar)

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
                // 필요한 경우 처리
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트가 변경될 때마다 호출됨
                val searchText = charSequence?.toString() ?: ""
                customAdapter.filter(searchText)
            }

            override fun afterTextChanged(editable: Editable?) {
                // EditText의 텍스트가 변경된 후에 호출됨
                // 필요한 경우 처리
            }
        })
        viewModel.contactInfo.observe(viewLifecycleOwner) { contactInfo ->
            if (contactInfo != null) {
                customAdapter.addItem(contactInfo.name, contactInfo.phoneNumber)
                customAdapter.setFilteredItemList(customAdapter.itemList)
            }
        }
//        val plusButton: AppCompatImageButton = view.findViewById(R.id.plus_button)
//        plusButton.setOnClickListener {
//            val dialog = Dialog(requireContext())
//            dialog.setContentView(R.layout.custom_dialog)
//
//            val name: EditText = dialog.findViewById(R.id.name)
//            val num: EditText = dialog.findViewById(R.id.num)
//
//            val closeButton: Button = dialog.findViewById(R.id.btnAdd)
//            closeButton.setOnClickListener {
//                customAdapter.addItem(name.text.toString(), num.text.toString())
//                val searchText = searchEditText.text.toString()
//                customAdapter.filter(searchText)
//                dialog.dismiss()
//            }
//            dialog.show()
//        }

        return view
    }
    private fun updateProfileDialog() {
        selectedProfile?.let { profile ->
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.proflie_dialog)

            val name: TextView = dialog.findViewById(R.id.nameText)
            val num: TextView = dialog.findViewById(R.id.numText)

            name.text = profile.name
            num.text = profile.num

            val closeButton: Button = dialog.findViewById(R.id.btnClose)
            closeButton.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }
    }
}

