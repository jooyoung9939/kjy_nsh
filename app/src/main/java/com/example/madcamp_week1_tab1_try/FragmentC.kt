package com.example.madcamp_week1_tab1_try

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.madcamp_week1_tab1_try.databinding.FragmentCBinding

class FragmentC : Fragment() {

    private lateinit var gAdapter: MyGridAdapter
    private lateinit var binding: FragmentCBinding

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }
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

        binding.galleryBtn.setOnClickListener{openGallery()}

        return rootView
    }
    fun updateImage(imageUri: Uri) {
        gAdapter.addImage(imageUri)
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

    private fun openGallery() {
        val pickImageIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(pickImageIntent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            val selectedImageUri: Uri? = data?.data
            gAdapter.addImage(selectedImageUri)
        }
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

            //imageView.setImageURI(picID[i])
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