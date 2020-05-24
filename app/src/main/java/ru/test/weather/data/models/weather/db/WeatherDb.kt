package ru.test.weather.data.models.weather.db

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class WeatherDb(var id: Short, var weatherUnitFormat: String, var type: String, var description: String,
                     var temperature: Float, var iconPath: String, var windSpeed: Float, var degrees: Float) : RealmObject() {
    @PrimaryKey
    var uniqueKey: String = "1"

    constructor() : this(0, "", "", "", 0F, "", 0F, 0F) {
    }
}