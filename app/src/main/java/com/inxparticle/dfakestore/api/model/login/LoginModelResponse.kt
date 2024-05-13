package com.inxparticle.dfakestore.api.model.login


import com.google.gson.annotations.SerializedName

data class LoginModelResponse(
    @SerializedName("token")
    val token: String
)