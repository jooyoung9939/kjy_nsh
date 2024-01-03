package com.example.madcamp_week1_tab1_try

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

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

        centerAdapter.addItem("해운대구 유기동물 입양센터", "051-749-5680","1", "5680", resourceIDtoUri(R.drawable.center_1), "해운대구에 위치한 유기동물 입양센터입니다.")
        centerAdapter.addItem("용인시 동물 보호센터", "031-324-3463","2", "3463", resourceIDtoUri(R.drawable.center_2), "용인시에 위치한 동물 보호센터입니다.")
        centerAdapter.addItem("서산시 동물 보호센터", "041-666-2747","3", "2747", resourceIDtoUri(R.drawable.center_3), "서산시에 위치한 동물 보호센터입니다.")
        centerAdapter.addItem("강릉시 동물 보호센터", "033-641-7515","4", "7515", resourceIDtoUri(R.drawable.center_4), "강릉시에 위치한 동물 보호센터입니다.")
        centerAdapter.addItem("대전광역시 동물 보호센터", "042-825-1118","5", "1118", resourceIDtoUri(R.drawable.center_5), "대전광역시에 위치한 동물 보호센터입니다.")
        centerAdapter.addItem("시흥시 동물누리 보호센터", "02-2060-2488","6", "2488", resourceIDtoUri(R.drawable.center_6), "시흥시에 위치한 동물누리 보호센터입니다.")
        centerAdapter.addItem("당진시 동물 보호소", "041-356-8210","7", "8210", resourceIDtoUri(R.drawable.center_7), "당진시에 위치한 동물 보호소입니다.")

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
            val centerPicture: ImageView = dialog.findViewById(R.id.centerURI)
            val centerText: TextView = dialog.findViewById(R.id.centerText)

            centerText.text = profile.centerText

            Glide.with(this@FragmentD)
                .load(profile.centerURI)
                .into(centerPicture)

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
    private fun resourceIDtoUri(resourceId: Int): Uri {
        return Uri.parse("android.resource://" + "com.example.madcamp_week1_tab1_try" + "/" + resourceId)
    }
}