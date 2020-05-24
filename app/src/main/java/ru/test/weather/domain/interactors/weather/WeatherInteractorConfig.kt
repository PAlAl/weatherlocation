package ru.test.weather.domain.interactors.weather

import javax.inject.Inject

class WeatherInteractorConfig @Inject constructor() : IWeatherInteractorConfig {
    override val weatherCacheTimeInMills: Long = 600_000
}