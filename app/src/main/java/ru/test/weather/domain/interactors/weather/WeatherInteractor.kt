package ru.test.weather.domain.interactors.weather

import io.reactivex.Single
import org.joda.time.DateTime
import ru.test.weather.domain.models.Language
import ru.test.weather.domain.models.Optional
import ru.test.weather.domain.models.location.WeatherPoint
import ru.test.weather.domain.models.weather.Weather
import ru.test.weather.domain.models.weather.types.WeatherUnitFormat
import ru.test.weather.domain.repositories.weather.IWeatherRepository
import javax.inject.Inject

class WeatherInteractor @Inject constructor(private val config: IWeatherInteractorConfig,
                                            private val repository: IWeatherRepository) : IWeatherInteractor {

    override fun getWeather(): Optional<Weather> {
        return if (isNeedUpdateByLastLoadDateTime(getLastLoadDateTime()))
            Optional.None
        else
            repository.getWeatherFromCache()
    }

    override fun loadWeather(locationPoint: WeatherPoint, isRefresh: Boolean): Single<Optional<Weather>> {
        return if (isRefresh || isNeedUpdateByLastLoadDateTime(getLastLoadDateTime()))
            repository.loadWeather(locationPoint, WeatherUnitFormat.Metric, Language.Ru)
        else
            Single.fromCallable { repository.getWeatherFromCache() }
    }

    private fun getLastLoadDateTime(): DateTime? {
        return repository.getLastLoadWeatherDateTime().let {
            when (it) {
                is Optional.Some -> it.data
                is Optional.None -> null
            }
        }
    }

    private fun isNeedUpdateByLastLoadDateTime(lastLoadDateTime: DateTime?): Boolean {
        return lastLoadDateTime == null || DateTime.now().millis - lastLoadDateTime.millis > config.weatherCacheTimeInMills
    }
}