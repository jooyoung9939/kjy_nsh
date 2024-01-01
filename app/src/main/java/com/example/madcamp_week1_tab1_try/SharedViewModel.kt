package com.example.madcamp_week1_tab1_try

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _selectedImage = MutableLiveData<Uri?>()
    val selectedImage: MutableLiveData<Uri?>
        get() = _selectedImage

    fun setSelectedImage(imageUri: Uri?) {
        _selectedImage.value = imageUri
    }
}