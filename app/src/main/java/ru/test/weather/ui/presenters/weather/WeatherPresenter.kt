package ru.test.weather.ui.presenters.weather

import ru.test.weather.domain.interactors.weather.IWeatherInteractor
import ru.test.weather.ui.presenters.BasePresenter
import javax.inject.Inject

class WeatherPresenter @Inject constructor(private val interactor: IWeatherInteractor) : BasePresenter<IWeatherView>() {
}