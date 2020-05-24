package ru.test.weather.ui.presenters.weather

import ru.test.weather.R
import ru.test.weather.domain.interactors.weather.IWeatherInteractor
import ru.test.weather.domain.models.Optional
import ru.test.weather.domain.models.location.WeatherPoint
import ru.test.weather.domain.models.weather.Weather
import ru.test.weather.domain.models.weather.types.WeatherTemperatureUnit
import ru.test.weather.domain.models.weather.types.WindDirection
import ru.test.weather.domain.system.ISchedulersProvider
import ru.test.weather.ui.presenters.BasePresenter
import ru.test.weather.ui.views.weather.models.WeatherViewModelMapper
import javax.inject.Inject
import javax.inject.Named

class WeatherPresenter @Inject constructor(private val interactor: IWeatherInteractor,
                                           private val schedulers: ISchedulersProvider,
                                           @Named("IMAGES_URL") private val imagesUrl: String) : BasePresenter<IWeatherView>() {

    private val imageSizePostfix = "@4x"
    private val imageExtension = ".png"

    init {
        loadWeather()
    }

    private fun loadWeather(isRefresh: Boolean = false) {
        interactor.loadWeather(WeatherPoint(55.06179327422091F, 38.74023914337159F), isRefresh)
                .observeOn(schedulers.ui())
                .doOnSubscribe {
                    viewState.changeBlockingProgress(true)
                }
                .doAfterTerminate {
                    viewState.changeBlockingProgress(false)
                }
                .subscribeDispose({
                    when (it) {
                        is Optional.Some -> updateData(it.data)
                        is Optional.None -> updateData(null)
                    }
                }, {
                    updateData(null)
                })
    }

    private fun updateData(model: Weather?) {
        model?.let {
            viewState.setData(WeatherViewModelMapper.toViewModel(it, getImageRotateByWindDirection(it.windDirection),
                    getImageUrl(it.iconPath), getTemperatureUnitIconResource(it.temperatureUnit)))
        } ?: viewState.setData(null)
    }

    private fun getImageRotateByWindDirection(windDirection: WindDirection): Float {
        return when (windDirection) {
            WindDirection.Northern -> 180F
            WindDirection.NorthEastern -> 225F
            WindDirection.Eastern -> 270F
            WindDirection.SouthEastern -> 315F
            WindDirection.Southern -> 0F
            WindDirection.SouthWestern -> 45F
            WindDirection.Western -> 90F
            WindDirection.NorthWestern -> 135F
        }
    }

    private fun getImageUrl(iconPath: String): String {
        return "$imagesUrl${iconPath}$imageSizePostfix$imageExtension"
    }

    private fun getTemperatureUnitIconResource(temperatureUnit: WeatherTemperatureUnit): Int {
        return when (temperatureUnit) {
            WeatherTemperatureUnit.Kelvin -> R.drawable.ic_temperature_kelvin
            WeatherTemperatureUnit.Fahrenheit -> R.drawable.ic_temperature_fahrenheit
            WeatherTemperatureUnit.Celsius -> R.drawable.ic_temperature_celsius
        }
    }

    fun onRefreshClick() {
        loadWeather(true)
    }
}