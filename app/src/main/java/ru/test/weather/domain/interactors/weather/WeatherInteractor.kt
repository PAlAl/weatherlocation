package ru.test.weather.domain.interactors.weather

import ru.test.weather.domain.repositories.weather.IWeatherRepository
import javax.inject.Inject

class WeatherInteractor @Inject constructor(private val repository: IWeatherRepository) : IWeatherInteractor {
}