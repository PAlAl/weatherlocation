package ru.test.weather.ui.global.location

import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest

interface IWeatherLocationManager {
    fun getLocation(locationRequest: LocationRequest, callback: LocationCallback)
    fun removeLocationUpdates(callback: LocationCallback)
}