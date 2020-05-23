package ru.test.weather.data.models.weather.response

import com.google.gson.annotations.SerializedName
import ru.test.weather.domain.models.weather.types.WeatherType

class WeatherResponse(@SerializedName("id") val id: Short,
                      @SerializedName("main") val type: WeatherType,
                      @SerializedName("description") val description: String,
                      @SerializedName("icon") val icon: String) {
}