package com.inxparticle.dfakestore.api

import com.inxparticle.dfakestore.api.model.login.LoginModelRequest
import com.inxparticle.dfakestore.api.model.login.LoginModelResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FakeStoreApi {

    @POST("auth/login")
    suspend fun userLogin(
        @Body
        loginModel :LoginModelRequest
    ) : LoginModelResponse
}