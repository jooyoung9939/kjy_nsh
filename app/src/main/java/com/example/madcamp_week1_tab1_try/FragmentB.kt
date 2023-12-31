package com.example.madcamp_week1_tab1_try

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.madcamp_week1_tab1_try.databinding.FragmentBBinding
import com.example.madcamp_week1_tab1_try.databinding.FragmentCBinding

class FragmentB : Fragment() {

    private lateinit var binding: FragmentBBinding
    companion object {
        private const val pick_image_request = 1
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.galleryBtn1.setOnClickListener{openGallery()}

        return rootView
    }

    private fun openGallery() {
        val pickImageIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(pickImageIntent, pick_image_request)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == pick_image_request && resultCode == Activity.RESULT_OK) {
            val selectedImageUri: Uri? = data?.data
            if (selectedImageUri != null) {
                (requireActivity() as? ImageUpdateListener)?.updateFragmentC(selectedImageUri)
            }
        }
    }


}