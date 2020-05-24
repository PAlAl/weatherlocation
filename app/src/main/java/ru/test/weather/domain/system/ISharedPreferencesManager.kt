package ru.test.weather.domain.system

interface ISharedPreferencesManager {
    fun putLong(key:String, value:Long)
    fun getLong(key:String):Long
}