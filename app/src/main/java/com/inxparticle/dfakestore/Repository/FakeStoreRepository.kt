package com.inxparticle.dfakestore.Repository

import com.inxparticle.dfakestore.api.RetrofitInstance
import com.inxparticle.dfakestore.api.model.login.LoginModelRequest
import com.inxparticle.dfakestore.api.model.login.LoginModelResponse
import com.inxparticle.dfakestore.util.Resource

class FakeStoreRepository {


    suspend fun loginBtnClicked(username:String,password:String) : Resource<LoginModelResponse> {
        val response = try {
            RetrofitInstance.api.userLogin(LoginModelRequest(password,username))
        } catch(e: Exception) {
            return Resource.Error("An unknown error occured. ${e.message}")
        }
        return Resource.Success(response)
    }

}