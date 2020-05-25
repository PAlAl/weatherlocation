package ru.test.weather.ui.presenters.main

import androidx.annotation.StringRes
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IMainView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun checkPermission(permission: String, callback: (Boolean) -> Unit)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun requestPermission(permission: String, requestCode: Int, @StringRes dialogTitle: Int, @StringRes dialogMessage: Int,
                          @StringRes dialogCancelTitle: Int, @StringRes dialogOkTitle: Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openWeatherScreen()

    fun checkGoogleApiAvailability()
}