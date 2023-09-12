package com.inxparticle.dfakestore.view.loginScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

const val  TAG = "loginScreenClicked"
class LoginScreenViewModel : ViewModel() {
    private var mMoveToDashBoardScreen= MutableLiveData(false)

    var moveToDashBoardScreen:LiveData<Boolean> = mMoveToDashBoardScreen
    private var showError : MutableLiveData<String> = MutableLiveData("")
    var mShowError : LiveData<String> = showError

    fun loginBtnClicked(){

        mMoveToDashBoardScreen.value = true

//        showError.value = "error occured"
    }
}