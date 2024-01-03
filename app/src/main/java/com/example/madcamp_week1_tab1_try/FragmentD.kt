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

class FragmentD : Fragment() {

    private lateinit var centerAdapter: CenterAdapter
    private lateinit var customAdapter: CustomAdapter

    private lateinit var recyclerView: RecyclerView

    private lateinit var searchEditText: EditText

    private lateinit var viewModel: SharedViewModel
    private lateinit var viewModel1: sharingnumber


    private var selectedCenter: CenterAdapter.centerItem? = null

    private var selectedProfile: CustomAdapter.Item? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_d, container, false)

        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        viewModel1 = ViewModelProvider(requireActivity())[sharingnumber::class.java]

        recyclerView = view.findViewById(R.id.centerRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        customAdapter = CustomAdapter(requireContext(), { position ->
            // 클릭 시 선택된 프로필 저장
            selectedProfile = customAdapter.getItem(position)
            // 다이얼로그 업데이트
            updateProfileDialog()
        })

        centerAdapter = CenterAdapter(requireContext(), { position ->
            // 클릭 시 선택된 프로필 저장
            selectedCenter = centerAdapter.getItem(position)
            // 다이얼로그 업데이트
            centerPWDialog()
        })

        centerAdapter.addItem("유기견 보호소 1", "055-542-7654","1", "1234")
        centerAdapter.addItem("유기견 보호소 2", "053-471-4274","2", "0000")
        centerAdapter.addItem("유기견 보호소 3", "050-434-7810","3", "9876")

        centerAdapter.setFilteredItemList(centerAdapter.centerItemList)

        recyclerView.adapter = centerAdapter

        searchEditText = view.findViewById(R.id.search_bar)

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
                // 필요한 경우 처리
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트가 변경될 때마다 호출됨
                val searchText = charSequence?.toString() ?: ""
                centerAdapter.filter(searchText)
            }

            override fun afterTextChanged(editable: Editable?) {
                // EditText의 텍스트가 변경된 후에 호출됨
                // 필요한 경우 처리
            }
        })

        return view
    }

    private fun centerPWDialog(){
        selectedCenter?.let { profile ->
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.center_password_dialog)

            val PW: EditText = dialog.findViewById(R.id.centerPW)
            val confirmButton: Button = dialog.findViewById(R.id.btnPW)

            confirmButton.setOnClickListener {
                val enteredPW = PW.text.toString()

                if (profile.centerPW == enteredPW) {
//                    customAdapter.setCenterTagFilter(profile.centerTag)
                    viewModel1.setcentertagnum(profile.centerTag)
//                    customAdapter.profileFilter()
                    navigateToProfileFragment()
                } else {
                    showPasswordMismatchDialog()
                }
                dialog.dismiss()
            }
            dialog.show()
        }
    }
    private fun navigateToProfileFragment() {
        val profileFragment = ProfileFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_profile, profileFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun showPasswordMismatchDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.center_password_fault_dialog)

        val exitButton: Button = dialog.findViewById(R.id.btnExit)
        exitButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
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