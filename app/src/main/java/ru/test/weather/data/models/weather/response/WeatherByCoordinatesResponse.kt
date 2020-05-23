package ru.test.weather.data.models.weather.response

import com.google.gson.annotations.SerializedName

class WeatherByCoordinatesResponse(@SerializedName("weather") val weathers: List<WeatherResponse>,
                                   @SerializedName("main") val mainInfo: WeatherMainResponse,
                                   @SerializedName("wind") val wind: WindResponse) {
}