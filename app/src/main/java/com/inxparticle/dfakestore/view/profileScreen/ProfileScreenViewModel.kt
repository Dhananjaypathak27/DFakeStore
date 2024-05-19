package com.inxparticle.dfakestore.view.profileScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ProfileScreenViewModel(app:Application):AndroidViewModel(app) {

    var mUserName = MutableLiveData("")
    var userName:LiveData<String> = mUserName
    init {
        mUserName.value = "Hi, Dhananjay Pathak"
    }
}