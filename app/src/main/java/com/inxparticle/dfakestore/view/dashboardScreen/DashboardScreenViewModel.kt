package com.inxparticle.dfakestore.view.dashboardScreen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.inxparticle.dfakestore.api.model.category.CategoryResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DashboardScreenViewModel(app:Application): AndroidViewModel(app) {
    val TAG = "DashboardScreenViewModel"
    private var mCategoryList = MutableLiveData<CategoryResponse>()
    var categoryList:LiveData<CategoryResponse> =mCategoryList
    init {
        Log.e(TAG, ": ititalization of viewmodel dashboard ${hashCode()}" )
        getListFromAPI()

    }

    private fun getListFromAPI() {
        viewModelScope.launch {
            delay(3000)

            val list = CategoryResponse()
            list.add("apple")
            list.add("banana")
            list.add("cat")
            list.add("rat")
            list.add("apple")
            list.add("banana")
            list.add("cat")
            list.add("rat")

            mCategoryList.value = list
        }
    }

}