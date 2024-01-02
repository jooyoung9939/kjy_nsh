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
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProfileFragment : Fragment() {

    private lateinit var customAdapter: CustomAdapter

    private lateinit var recyclerView: RecyclerView

    private lateinit var searchEditText: EditText

    private lateinit var viewModel: SharedViewModel
    private lateinit var viewModel1: sharingnumber

    private var selectedProfile: CustomAdapter.Item? = null


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.member_dialog, container, false)

        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        viewModel1 = ViewModelProvider(requireActivity())[sharingnumber::class.java]

        recyclerView = view.findViewById(R.id.RecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        customAdapter = CustomAdapter(requireContext(), { position ->
            // 클릭 시 선택된 프로필 저장
            selectedProfile = customAdapter.getItem(position)
            // 다이얼로그 업데이트
            updateProfileDialog()
        })

        customAdapter.addItem("조유리", "010-1100-0000","1")
        customAdapter.addItem("박재범", "010-8292-5237","2")
        customAdapter.addItem("김채원", "010-8729-7897", "3")

        viewModel1.centertagnum.observe(viewLifecycleOwner){ centertagnum ->
            if (centertagnum != null) {
                customAdapter.profileFilter(centertagnum)
            }
        }

        customAdapter.setFilteredItemList()

        recyclerView.adapter = customAdapter

        searchEditText = view.findViewById(R.id.profile_search_bar)

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
                customAdapter.addItem(contactInfo.name, contactInfo.phoneNumber, contactInfo.centertag)
                viewModel1.centertagnum.observe(viewLifecycleOwner){ centertagnum ->
                    if (centertagnum != null) {
                        customAdapter.profileFilter(centertagnum)
                    }
                }
            }
        }

        val closeButton: Button = view.findViewById(R.id.btnClose)
        closeButton.setOnClickListener {
            navigateToFragmentD()
        }

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

    private fun navigateToFragmentD() {
        val FragmentD = FragmentD()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_profile, FragmentD)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

