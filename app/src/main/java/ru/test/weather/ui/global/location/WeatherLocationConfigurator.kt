package ru.test.weather.ui.global.location

import android.location.Location
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WeatherLocationConfigurator @Inject constructor() : IWeatherLocationConfigurator {
    override fun getDefaultLocationRequest(): LocationRequest = LocationRequest().apply {
        interval = TimeUnit.SECONDS.toMillis(60)
        fastestInterval = TimeUnit.SECONDS.toMillis(30)
        maxWaitTime = TimeUnit.SECONDS.toMillis(5)
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    override fun getLocationCallback(onSuccess: (Location) -> Unit, onFailed: (locationAvailability: LocationAvailability?) -> Unit): LocationCallback {
        return WeatherLocationCallback(onSuccess, onFailed)
    }
}