package ru.test.weather.data.repositories.weather

import io.reactivex.Single
import org.joda.time.DateTime
import ru.test.weather.data.models.weather.mappers.WeatherMapper
import ru.test.weather.data.network.IApiService
import ru.test.weather.data.repositories.cache.IWeatherCache
import ru.test.weather.domain.models.Language
import ru.test.weather.domain.models.Optional
import ru.test.weather.domain.models.location.WeatherPoint
import ru.test.weather.domain.models.weather.Weather
import ru.test.weather.domain.models.weather.types.WeatherUnitFormat
import ru.test.weather.domain.repositories.weather.IWeatherRepository
import ru.test.weather.domain.system.ISchedulersProvider
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: IApiService, private val schedulers: ISchedulersProvider,
                                            private val cache: IWeatherCache) : IWeatherRepository {

    override fun loadWeather(locationPoint: WeatherPoint, unitFormat: WeatherUnitFormat, language: Language): Single<Optional<Weather>> {
        return api.getWeatherByCoordinates(locationPoint.latitude, locationPoint.longitude, unitFormat.toString(), language.toString())
                .subscribeOn(schedulers.io())
                .map { weatherByCoordinatesResponse ->
                    WeatherMapper.fromResponse(weatherByCoordinatesResponse, unitFormat)?.let {
                        cache.save(WeatherMapper.toDbModel(it))
                        cache.setLastLoadDateTime(DateTime.now())
                        Optional.Some(it)
                    } ?: Optional.None
                }
    }
}