package com.example.madcamp_week1_tab1_try

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_week1_tab1_try.databinding.FragmentABinding
import org.json.JSONObject

class FragmentA : Fragment() {

    private lateinit var binding: FragmentABinding

//    // 1. Context를 할당할 변수를 프로퍼티로 선언(어디서든 사용할 수 있게)
//    lateinit var mainActivity: MainActivity
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//
//        // 2. Context를 액티비티로 형변환해서 할당
//        mainActivity = context as MainActivity}

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val json = assets.open("test.json").reader().readText()
//        val data = JSONObject(json).getJSONObject("data")
//
//        binding.recyclerView.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        binding.recyclerView.adapter = RecyclerViewAdapter(data)
//        binding.recyclerView.addItemDecoration(
//            DividerItemDecoration(
//                this,
//                DividerItemDecoration.VERTICAL
//            )
//        )
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentABinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val json = requireContext().assets.open("test.json").reader().readText()
        val data = JSONObject(json).getJSONObject("data")

        binding?.recyclerView?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding?.recyclerView?.adapter = RecyclerViewAdapter(data)
        binding?.recyclerView?.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }
}

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentABinding.inflate(inflater, container, false)
//        return binding!!.root
//        return inflater.inflate(R.layout.fragment_a, container, false);
//    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val recycler_view = mainActivity.findViewById<RecyclerView>(R.id.recycler_view)
//
//        val list = ArrayList<UserData>()
//        list.add(UserData("김주영", "7452524"))
//        list.add(UserData("남승훈", "262623"))
//        list.add(UserData("강승완", "65262653"))
//
//        val adapter = RecyclerViewAdapter(list)
//        RecyclerViewAdapter.notifyDataSetChanged()
//
//        recycler_view.adapter = adapter
//        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//    }
//}