package com.example.madcamp_week1_tab1_try

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
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
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        // Observe changes in selectedImageUri
        viewModel.contactInfo.observe(viewLifecycleOwner) { contactInfo ->
            if (contactInfo != null) {
                gAdapter.addImage(contactInfo)
            }
        }
        return rootView
    }

    private fun showLargeImageDialog(position: Int) {
        val dialogView = View.inflate(requireContext(), R.layout.dialog, null)
        val dlg = AlertDialog.Builder(requireContext())
        val ivPic = dialogView.findViewById<ImageView>(R.id.ivPic)
        ivPic.setImageURI(gAdapter.picID[position].imageUri)

        val tvName = dialogView.findViewById<TextView>(R.id.tvName)
        val tvPhoneNumber = dialogView.findViewById<TextView>(R.id.tvPhoneNumber)
        tvName.text = gAdapter.picID[position].name
        tvPhoneNumber.text = gAdapter.picID[position].phoneNumber

        dlg.setTitle("큰 이미지")
        dlg.setIcon(R.drawable.ic_launcher)
        dlg.setView(dialogView)
        dlg.setNegativeButton("닫기", null)
        dlg.show()
    }
    inner class MyGridAdapter(private val context: Context) : BaseAdapter() {

        val picID = ArrayList<ContactInfo>().apply {
        add(ContactInfo("남승훈","1234",resourceIDtoUri(context, R.drawable.doggy_1)))
        add(ContactInfo("남승훈","1234",resourceIDtoUri(context, R.drawable.doggy_1)))
        add(ContactInfo("남승훈","1234",resourceIDtoUri(context, R.drawable.doggy_1)))
        }

        private fun resourceIDtoUri(context: Context, resourceId: Int): Uri {
            return Uri.parse("android.resource://" + context.packageName + "/" + resourceId)
        }

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
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setPadding(5, 5, 5, 5)

            Glide.with(this@FragmentC)
                .load(picID[i].imageUri)
                .into(imageView)

            return imageView
        }
        fun addImage(contact: ContactInfo?){
            contact?.let{
                picID.add(it)
            }
            notifyDataSetChanged()
        }
    }
}