package ru.test.weather.ui.global.location

import android.location.Location
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest

interface IWeatherLocationConfigurator {
    fun getDefaultLocationRequest(): LocationRequest
    fun getLocationCallback(onSuccess: (Location) -> Unit, onFailed: () -> Unit): LocationCallback
}