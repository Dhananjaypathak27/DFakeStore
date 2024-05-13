package com.inxparticle.dfakestore.api.model.login


import com.google.gson.annotations.SerializedName

data class LoginModelRequest(
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)