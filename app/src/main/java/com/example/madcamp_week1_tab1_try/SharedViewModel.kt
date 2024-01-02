package com.example.madcamp_week1_tab1_try

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class ContactInfo(val name: String, val phoneNumber: String, val imageUri: Uri?, val dogname: String, val centertag: String, var dogstate: String)

class SharedViewModel : ViewModel() {
    private val _selectedImage = MutableLiveData<Uri?>()
    val selectedImage: MutableLiveData<Uri?>
        get() = _selectedImage

    private val _contactInfo = MutableLiveData<ContactInfo>()
    val contactInfo: MutableLiveData<ContactInfo>
        get() = _contactInfo
    fun setSelectedImage(imageUri: Uri?) {
        _selectedImage.value = imageUri
    }
    fun setContactInfo(name: String, phoneNumber: String, imageUri: Uri?, dogname: String, centertag: String, dogstate: String) {
        val contactInfo = ContactInfo(name, phoneNumber, imageUri, dogname, centertag, dogstate)
        _contactInfo.value = contactInfo
    }
}

class sharingnumber : ViewModel(){
    private val _centertagnum = MutableLiveData<String>()
    val centertagnum: MutableLiveData<String>
        get() = _centertagnum
    fun setcentertagnum(num: String) {
        _centertagnum.value = num
    }
}