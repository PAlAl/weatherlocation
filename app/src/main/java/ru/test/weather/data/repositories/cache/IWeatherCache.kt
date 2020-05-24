package ru.test.weather.data.repositories.cache

import org.joda.time.DateTime
import ru.test.weather.data.models.weather.db.WeatherDb

interface IWeatherCache {
    fun getWeatherDb(): WeatherDb?
    fun save(weather: WeatherDb)
    fun getLastLoadDateTime(): DateTime?
    fun setLastLoadDateTime(date: DateTime)
}