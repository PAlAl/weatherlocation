package ru.test.weather.data.models.weather.response

import com.google.gson.annotations.SerializedName

class WindResponse(@SerializedName("speed") val speed:Float,
                   @SerializedName("deg") val degrees:Float) {
}