package ru.test.weather.ui.presenters.weather

import ru.test.weather.domain.interactors.weather.IWeatherInteractor
import ru.test.weather.domain.models.location.WeatherPoint
import ru.test.weather.domain.system.ISchedulersProvider
import ru.test.weather.ui.presenters.BasePresenter
import javax.inject.Inject

class WeatherPresenter @Inject constructor(private val interactor: IWeatherInteractor,
                                           private val schedulers: ISchedulersProvider) : BasePresenter<IWeatherView>() {

    init {
        loadWeather()
    }

    private fun loadWeather() {
        interactor.loadWeather(WeatherPoint(55.06179327422091F, 38.74023914337159F))
                .observeOn(schedulers.ui())
                .subscribeDispose({
                    val a = it
                }, {
                    val a = it
                })
    }
}