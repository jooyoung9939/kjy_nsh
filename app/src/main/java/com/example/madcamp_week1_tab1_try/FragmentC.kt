package com.example.madcamp_week1_tab1_try

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.madcamp_week1_tab1_try.databinding.FragmentCBinding

class FragmentC : Fragment() {

    private lateinit var gAdapter: MyGridAdapter
    private lateinit var binding: FragmentCBinding
    private lateinit var viewModel: SharedViewModel
    private var showlostdogs = true
    private var letsgo = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCBinding.inflate(inflater, container, false)
        val rootView = binding.root
        gAdapter = MyGridAdapter(requireContext())
        binding.gridView.adapter = gAdapter
        binding.gridView.setOnItemClickListener { _, _, position, _ ->
            showLargeImageDialog(position)
        }
        binding.sortBtn.setOnClickListener{
            showlostdogs=!showlostdogs
            //gAdapter.filter()
            gAdapter.notifyDataSetChanged()
        }
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        // Observe changes in selectedImageUri
        viewModel.contactInfo.observe(viewLifecycleOwner) { contactInfo ->
            if (contactInfo != null) {
                Log.d("go", "add")
                gAdapter.addImage(contactInfo)
                gAdapter.notifyDataSetChanged()
            }
        }
        return rootView
    }
    private fun showLargeImageDialog(position: Int) {
        val dialogView = View.inflate(requireContext(), R.layout.dialog, null)
        val dlg = AlertDialog.Builder(requireContext())
        val ivPic = dialogView.findViewById<ImageView>(R.id.ivPic)
        val tvName = dialogView.findViewById<TextView>(R.id.tvName)
        val tvPhoneNumber = dialogView.findViewById<TextView>(R.id.tvPhoneNumber)
        val tvdogName = dialogView.findViewById<TextView>(R.id.tvdogName)
        val tvcentertag = dialogView.findViewById<TextView>(R.id.tVcentertag)
        val tvdogstate = dialogView.findViewById<TextView>(R.id.tVdogstate)
        val changebtn = dialogView.findViewById<ImageButton>(R.id.change_btn)

        if(showlostdogs){
            ivPic.setImageURI(gAdapter.picID[position].imageUri)
            tvName.text = gAdapter.picID[position].name
            tvPhoneNumber.text = gAdapter.picID[position].phoneNumber
            tvdogName.text = gAdapter.picID[position].dogname
            tvcentertag.text = gAdapter.picID[position].centertag
            tvdogstate.text = gAdapter.picID[position].dogstate

            dlg.setTitle("큰 이미지")
            dlg.setIcon(R.drawable.ic_launcher)
            dlg.setView(dialogView)
            dlg.setNegativeButton("닫기", null)

            changebtn.setOnClickListener{
                if (gAdapter.picID[position].dogstate == "잃어버림"){
                    gAdapter.picID[position].dogstate = "안 잃어버림"
                    tvdogstate.text = "안 잃어버림"
                    gAdapter.filter()
                }
                else{
                    gAdapter.picID[position].dogstate = "잃어버림"
                    tvdogstate.text = "잃어버림"
                    gAdapter.filter()
                }
                !letsgo
            }
        }
        else{
            ivPic.setImageURI(gAdapter.filtered_picID[position].imageUri)
            tvName.text = gAdapter.filtered_picID[position].name
            tvPhoneNumber.text = gAdapter.filtered_picID[position].phoneNumber
            tvdogName.text = gAdapter.filtered_picID[position].dogname
            tvcentertag.text = gAdapter.filtered_picID[position].centertag
            tvdogstate.text = gAdapter.filtered_picID[position].dogstate

            dlg.setTitle("큰 이미지")
            dlg.setIcon(R.drawable.ic_launcher)
            dlg.setView(dialogView)
            dlg.setNegativeButton("닫기", null)

            changebtn.setOnClickListener{
                if (gAdapter.filtered_picID[position].dogstate == "잃어버림"){
                    for(item in gAdapter.picID){
                        if(gAdapter.filtered_picID[position].imageUri==item.imageUri){
                            item.dogstate="안 잃어버림"
                        }
                    }
                    tvdogstate.text = "안 잃어버림"
                    gAdapter.filter()
                }
                else{
                    for(item in gAdapter.picID){
                        if(gAdapter.filtered_picID[position].imageUri==item.imageUri){
                            item.dogstate="잃어버림"
                        }
                    }
                    tvdogstate.text = "잃어버림"
                    gAdapter.filter()
                }
                !letsgo
            }
        }

        dlg.show()
    }
    inner class MyGridAdapter(private val context: Context) : BaseAdapter() {

        val picID = ArrayList<ContactInfo>().apply {
            add(ContactInfo("남승훈", "1234", resourceIDtoUri(context, R.drawable.doggy_1), "뭉치", "1", "잃어버림"))
            add(ContactInfo("남승훈", "1234", resourceIDtoUri(context, R.drawable.doggy_2), "뭉치", "1", "잃어버림"))
            add(ContactInfo("남승훈", "1234", resourceIDtoUri(context, R.drawable.doggy_3), "뭉치", "1", "잃어버림"))
        }
        val filtered_picID = ArrayList<ContactInfo>().apply {
            add(ContactInfo("남승훈", "1234", resourceIDtoUri(context, R.drawable.doggy_1), "뭉치", "1", "잃어버림"))
            add(ContactInfo("남승훈", "1234", resourceIDtoUri(context, R.drawable.doggy_2), "뭉치", "1", "잃어버림"))
            add(ContactInfo("남승훈", "1234", resourceIDtoUri(context, R.drawable.doggy_3), "뭉치", "1", "잃어버림"))

        }
        private fun resourceIDtoUri(context: Context, resourceId: Int): Uri {
            return Uri.parse("android.resource://" + context.packageName + "/" + resourceId)
        }

        override fun getCount(): Int {
            if(showlostdogs){
                return picID.size
            }
            else{
                return filtered_picID.size
            }
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
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setPadding(5, 5, 5, 5)

            if(showlostdogs){
                Log.d("GridView", "picID before show: $picID")
                Glide.with(this@FragmentC)
                    .load(picID[i].imageUri)
                    .into(imageView)
            }
            else{
                Glide.with(this@FragmentC)
                    .load(filtered_picID[i].imageUri)
                    .into(imageView)
            }
            return imageView
        }
        fun addImage(contact: ContactInfo?) {
            contact?.let {
                picID.add(it)
                Log.d("GridView", "picID after adding: $picID")
            }
            notifyDataSetChanged()
            binding.gridView.requestLayout()
            Log.d("GridView", "picID size after adding: ${getCount()}")

        }
        fun filter() {
            filtered_picID.clear()
            for(item in picID){
                if(item.dogstate=="잃어버림"){
                    filtered_picID.add(item)
                }
            }
            notifyDataSetChanged()
            Log.d("Adapter", "notifyDataSetChanged() called")  // 로그 추가
        }
    }
}