package ru.test.weather.domain.interactors.weather

import io.reactivex.Single
import ru.test.weather.domain.models.Optional
import ru.test.weather.domain.models.location.WeatherPoint
import ru.test.weather.domain.models.weather.Weather

interface IWeatherInteractor {
    fun loadWeather(locationPoint: WeatherPoint): Single<Optional<Weather>>
}