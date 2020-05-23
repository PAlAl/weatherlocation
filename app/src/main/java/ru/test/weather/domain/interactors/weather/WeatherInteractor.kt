package ru.test.weather.domain.interactors.weather

import io.reactivex.Single
import ru.test.weather.domain.models.Language
import ru.test.weather.domain.models.Optional
import ru.test.weather.domain.models.location.WeatherPoint
import ru.test.weather.domain.models.weather.Weather
import ru.test.weather.domain.models.weather.types.WeatherUnitFormat
import ru.test.weather.domain.repositories.weather.IWeatherRepository
import javax.inject.Inject

class WeatherInteractor @Inject constructor(private val repository: IWeatherRepository) : IWeatherInteractor {

    override fun loadWeather(locationPoint: WeatherPoint): Single<Optional<Weather>> {
        return repository.loadWeather(locationPoint, WeatherUnitFormat.Metric, Language.Ru)
    }
}