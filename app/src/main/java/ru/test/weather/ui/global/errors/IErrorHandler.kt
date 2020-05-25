package ru.test.weather.ui.global.errors

import androidx.annotation.StringRes

interface IErrorHandler {
    fun proceed(error: Throwable)
    fun proceed(@StringRes resId: Int)
}