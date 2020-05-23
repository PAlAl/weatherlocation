package ru.test.weather.domain.models.weather.types

import com.google.gson.annotations.SerializedName

enum class WeatherType {
    @SerializedName("Thunderstorm")
    Thunderstorm,

    @SerializedName("Drizzle")
    Drizzle,

    @SerializedName("Rain")
    Rain,

    @SerializedName("Snow")
    Snow,

    @SerializedName("Mist")
    Mist,

    @SerializedName("Smoke")
    Smoke,

    @SerializedName("Haze")
    Haze,

    @SerializedName("Dust")
    Dust,

    @SerializedName("Fog")
    Fog,

    @SerializedName("Sand")
    Sand,

    @SerializedName("Ash")
    Ash,

    @SerializedName("Squall")
    Squall,

    @SerializedName("Tornado")
    Tornado,

    @SerializedName("Clear")
    Clear,

    @SerializedName("Clouds")
    Clouds,
}