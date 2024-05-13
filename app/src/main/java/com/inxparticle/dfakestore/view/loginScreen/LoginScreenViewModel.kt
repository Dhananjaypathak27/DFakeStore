package com.inxparticle.dfakestore.view.loginScreen

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inxparticle.dfakestore.Repository.FakeStoreRepository
import com.inxparticle.dfakestore.util.IS_USER_LOGGED_IN
import com.inxparticle.dfakestore.util.Resource
import com.inxparticle.dfakestore.util.SharedPref
import com.inxparticle.dfakestore.util.TOKEN
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val  TAG = "loginScreenClicked"
class LoginScreenViewModel(context: Application) : AndroidViewModel(context) {
    private val repository = FakeStoreRepository()

    private val sharedPref = SharedPref(context)

    private var mMoveToLoggedInGraph= MutableLiveData(false)
    var moveToLoggedInGraph:MutableLiveData<Boolean> = mMoveToLoggedInGraph

    private val mShowError : MutableLiveData<String?> = MutableLiveData("")
    var showError : LiveData<String?> = mShowError

    private val mIsLoading = MutableLiveData(false)
    var isLoading:LiveData<Boolean> = mIsLoading

    var username = MutableLiveData("mor_2314")
    var password = MutableLiveData("83r5^_")

    fun onClickButton() {
        viewModelScope.launch {

            if(!username.value.isNullOrBlank() && !password.value.isNullOrBlank()){
                Log.e(TAG, "onClickButton 1: ${mIsLoading.value}", )
                mIsLoading.value = true
                delay(4000)
                Log.e(TAG, "onClickButton 2: ${mIsLoading.value}", )
                val result = repository.loginBtnClicked(username.value!!,password.value!!)
                when(result){
                    is Resource.Success ->{
                        mMoveToLoggedInGraph.value = true
                        mIsLoading.value = false
                        sharedPref.setBoolean(IS_USER_LOGGED_IN,true)
                        sharedPref.setString(TOKEN,result.data!!.token)
                        Log.e(TAG, "onClickButton 3: ${mIsLoading.value}", )
                    }

                    is Resource.Error -> {
                        mShowError.value = result.message
                        mIsLoading.value = false
                    }
                    is Resource.Loading -> {
                        mIsLoading.value = true
                    }
                }
            }
        }
    }

}