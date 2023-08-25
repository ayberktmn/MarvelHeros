package com.ayberk.marvelheros

import android.content.SharedPreferences
import javax.inject.Inject

class SessionManager @Inject constructor(private val  preferences: SharedPreferences){
    fun getIsFirstRun() = preferences.getBoolean(com.ayberk.marvelheros.Constants.FIRST_RUN_KEY,true)

    fun setIsFirstRun(value: Boolean){
        val editor = preferences.edit()
        editor.putBoolean(com.ayberk.marvelheros.Constants.FIRST_RUN_KEY,value)
        editor.apply()
    }
}