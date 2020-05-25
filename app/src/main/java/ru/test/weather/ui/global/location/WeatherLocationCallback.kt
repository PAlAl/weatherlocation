package ru.test.weather.ui.global.location

import android.location.Location
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult

class WeatherLocationCallback(private val onSuccess: (Location) -> Unit,
                              private val onFailed: (locationAvailability: LocationAvailability?) -> Unit) : LocationCallback() {
    override fun onLocationResult(location: LocationResult?) {
        location?.let {
            onSuccess(it.lastLocation)
        }
    }

    override fun onLocationAvailability(locationAvailability: LocationAvailability?) {
        onFailed(locationAvailability)
    }
}