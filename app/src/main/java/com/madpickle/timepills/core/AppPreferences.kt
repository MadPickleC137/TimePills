package com.madpickle.timepills.core

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.madpickle.timepills.utils.APP_PACKAGE_HASH
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppPreferences @Inject constructor(@ApplicationContext context : Context) {
    private val prefs =  context.getSharedPreferences(APP_PACKAGE_HASH, MODE_PRIVATE)

    private val FIRST_RUN_KEY = "first-run"

    fun isFirstRun(): Boolean {
        return prefs.getBoolean(FIRST_RUN_KEY, true)
    }

    fun setIsFirstRun(){
        prefs.edit().putBoolean(FIRST_RUN_KEY, false).apply()
    }
}