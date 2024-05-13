package com.inxparticle.dfakestore.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

const val PREFERENCE_NAME = "fake_store_pref"
const val IS_USER_LOGGED_IN = "is_user_logged_in"
const val TOKEN = "token"
class SharedPref(context: Context) {
    private val pref: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    private val editor = pref.edit()
    private val gson = Gson()

    fun getBoolean(value: String):Boolean{
        return pref.getBoolean(value,false)
    }

    fun setBoolean(key :String,value:Boolean){
        editor.putBoolean(key,value)
        editor.apply()
    }

    fun getString(value: String):String?{
        return pref.getString(value,"")
    }

    fun setString(key :String,value:String){
        editor.putString(key,value)
        editor.apply()
    }
}