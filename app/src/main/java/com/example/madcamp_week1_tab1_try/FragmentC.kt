package com.example.madcamp_week1_tab1_try

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

class FragmentC : Fragment() {

    private lateinit var gAdapter: MyGridAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_c, container, false)

        val gv = rootView.findViewById<GridView>(R.id.gridView)
        gAdapter = MyGridAdapter(requireContext())

        gv.adapter = gAdapter

        gv.setOnItemClickListener { _, _, position, _ ->
            showLargeImageDialog(position)
        }

        return rootView
    }

    private fun showLargeImageDialog(position: Int) {
        val dialogView = View.inflate(requireContext(), R.layout.dialog, null)
        val dlg = AlertDialog.Builder(requireContext())
        val ivPic = dialogView.findViewById<ImageView>(R.id.ivPic)
        ivPic.setImageResource(gAdapter.picID[position])
        dlg.setTitle("큰 이미지")
        dlg.setIcon(R.drawable.ic_launcher)
        dlg.setView(dialogView)
        dlg.setNegativeButton("닫기", null)
        dlg.show()
    }

    inner class MyGridAdapter(private val context: Context) : BaseAdapter() {

        val picID = arrayOf(
            R.drawable.member_1, R.drawable.member_2, R.drawable.member_3, R.drawable.member_4, R.drawable.member_5,
            R.drawable.member_6, R.drawable.member_7, R.drawable.member_8, R.drawable.member_9, R.drawable.member_1,
            R.drawable.member_1, R.drawable.member_1, R.drawable.member_1, R.drawable.member_1, R.drawable.member_1,
            R.drawable.member_1, R.drawable.member_1, R.drawable.member_1, R.drawable.member_1, R.drawable.member_1,
            R.drawable.member_1, R.drawable.member_1, R.drawable.member_1, R.drawable.member_1, R.drawable.member_1,
            R.drawable.member_1, R.drawable.member_1, R.drawable.member_1, R.drawable.member_1, R.drawable.member_1
        )

        override fun getCount(): Int {
            return picID.size
        }

        override fun getItem(i: Int): Any? {
            return null
        }

        override fun getItemId(i: Int): Long {
            return 0
        }

        override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View {
            val imageView = ImageView(context)
            imageView.layoutParams = ViewGroup.LayoutParams(400, 300)
            imageView.scaleType = ImageView.ScaleType.FIT_CENTER
            imageView.setPadding(5, 5, 5, 5)

            imageView.setImageResource(picID[i])

            return imageView
        }
    }
}