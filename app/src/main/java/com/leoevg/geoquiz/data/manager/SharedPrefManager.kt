package com.leoevg.geoquiz.data.manager

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPrefManager @Inject constructor(
    // Shared Preferences - быстрые настройки при запуске приложения
    @ApplicationContext
    private val context: Context
) {
    private val prefs = context.getSharedPreferences("geoPrefs", Context.MODE_PRIVATE)

    fun getBoolValueByKey(key: String, defaultValue: Boolean = true): Boolean {
        return prefs.getBoolean(key, defaultValue)
    }
    fun putBoolValue(key: String, newValue: Boolean){
        prefs.edit().putBoolean(key, newValue).apply()
    }
}











