package ru.test.weather.data.models.weather.response

import com.google.gson.annotations.SerializedName

class WeatherMainResponse(@SerializedName("temp") val temperature: Float) {
}