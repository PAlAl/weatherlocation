package ru.test.weather.ui.views.weather.models

import androidx.annotation.StringRes

class WeatherNoDataViewModel(@StringRes val message: Int, @StringRes val  actionMessage: Int, val  action: () -> Unit) {
}