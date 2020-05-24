package ru.test.weather.ui.global.managers

import android.content.Context
import android.content.SharedPreferences
import ru.test.weather.domain.system.ISharedPreferencesManager
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(context: Context) : ISharedPreferencesManager {

    private val sp: SharedPreferences = context.getSharedPreferences(context.packageName + "weather_storage", Context.MODE_PRIVATE)

    override fun putLong(key: String, value: Long) {
        sp.edit().putLong(key, value).apply()
    }

    override fun getLong(key: String): Long {
        return sp.getLong(key, 0)
    }
}