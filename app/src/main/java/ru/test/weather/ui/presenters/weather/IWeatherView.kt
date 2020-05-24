package ru.test.weather.ui.presenters.weather

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.test.weather.ui.views.weather.models.WeatherNoDataViewModel
import ru.test.weather.ui.views.weather.models.WeatherViewModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface IWeatherView : MvpView {
    fun setData(model: WeatherViewModel?, noDataModel: WeatherNoDataViewModel? = null)
    fun changeBlockingProgress(isShow: Boolean)

    @StateStrategyType(SkipStrategy::class)
    fun openSettings()
}