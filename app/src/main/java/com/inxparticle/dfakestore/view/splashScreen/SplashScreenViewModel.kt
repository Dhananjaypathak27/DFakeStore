package com.inxparticle.dfakestore.view.splashScreen

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashScreenViewModel : ViewModel(){
    private var mMoveToNextScreen = MutableLiveData<Boolean>(false)
    var moveToNextScreen:LiveData<Boolean> = mMoveToNextScreen
    init {
        startCountDown()
    }

    private fun startCountDown() {
//        viewModelScope.launch(Dispatchers.Default) {


            val timer = object: CountDownTimer(3000, 3000) {
                  override fun onTick(millisUntilFinished: Long) {
                      Log.e("TAG", "onTick: "+ Thread.currentThread().name )
                }

                override fun onFinish() {
                    mMoveToNextScreen.value = true
                }
            }
            timer.start()
//        }
    }
}