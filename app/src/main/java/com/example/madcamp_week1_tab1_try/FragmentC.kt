package com.example.madcamp_week1_tab1_try

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
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
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        // Observe changes in selectedImageUri
        viewModel.selectedImage.observe(viewLifecycleOwner) { imageUri ->
            if (imageUri != null) {
                gAdapter.addImage(imageUri)
            }
        }
        return rootView
    }

    private fun showLargeImageDialog(position: Int) {
        val dialogView = View.inflate(requireContext(), R.layout.dialog, null)
        val dlg = AlertDialog.Builder(requireContext())
        val ivPic = dialogView.findViewById<ImageView>(R.id.ivPic)
        ivPic.setImageURI(gAdapter.picID[position])
        dlg.setTitle("큰 이미지")
        dlg.setIcon(R.drawable.ic_launcher)
        dlg.setView(dialogView)
        dlg.setNegativeButton("닫기", null)
        dlg.show()
    }
    inner class MyGridAdapter(private val context: Context) : BaseAdapter() {

        val picID = mutableListOf<Uri>(
            resourceIDtoUri(context, R.drawable.doggy_1),
            resourceIDtoUri(context, R.drawable.doggy_2),
            resourceIDtoUri(context, R.drawable.doggy_3),
            resourceIDtoUri(context, R.drawable.doggy_4),
            resourceIDtoUri(context, R.drawable.doggy_5),
            resourceIDtoUri(context, R.drawable.doggy_6),
            resourceIDtoUri(context, R.drawable.doggy_7),
            resourceIDtoUri(context, R.drawable.doggy_1),
            resourceIDtoUri(context, R.drawable.doggy_2),
            resourceIDtoUri(context, R.drawable.doggy_3),
            resourceIDtoUri(context, R.drawable.doggy_4),
            resourceIDtoUri(context, R.drawable.doggy_5),
            resourceIDtoUri(context, R.drawable.doggy_6),
            resourceIDtoUri(context, R.drawable.doggy_7),
            resourceIDtoUri(context, R.drawable.doggy_1),
            resourceIDtoUri(context, R.drawable.doggy_2),
            resourceIDtoUri(context, R.drawable.doggy_3),
            resourceIDtoUri(context, R.drawable.doggy_4),
            resourceIDtoUri(context, R.drawable.doggy_5),
            resourceIDtoUri(context, R.drawable.doggy_6),
            resourceIDtoUri(context, R.drawable.doggy_7)
        )

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
                .load(picID[i])
                .into(imageView)

            return imageView
        }
        fun addImage(imageUri: Uri?){
            imageUri?.let{
                picID.add(it)
            }
            notifyDataSetChanged()
        }
    }
}