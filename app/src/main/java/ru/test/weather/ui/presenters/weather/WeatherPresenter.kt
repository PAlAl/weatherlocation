package ru.test.weather.ui.presenters.weather

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import com.google.android.gms.location.LocationCallback
import ru.test.weather.R
import ru.test.weather.domain.interactors.weather.IWeatherInteractor
import ru.test.weather.domain.models.Optional
import ru.test.weather.domain.models.location.WeatherPoint
import ru.test.weather.domain.models.weather.Weather
import ru.test.weather.domain.models.weather.types.WeatherTemperatureUnit
import ru.test.weather.domain.models.weather.types.WindDirection
import ru.test.weather.domain.system.ISchedulersProvider
import ru.test.weather.ui.global.LOCATION_PERMISSIONS_REQUEST_CODE
import ru.test.weather.ui.global.errors.IErrorHandler
import ru.test.weather.ui.global.eventBus.IBus
import ru.test.weather.ui.global.eventBus.IBusNotifier
import ru.test.weather.ui.global.eventBus.permissions.CheckPermissionEvent
import ru.test.weather.ui.global.eventBus.permissions.PermissionResultEvent
import ru.test.weather.ui.global.eventBus.permissions.RequestPermissionEvent
import ru.test.weather.ui.global.location.WeatherLocationConfigurator
import ru.test.weather.ui.global.location.WeatherLocationManager
import ru.test.weather.ui.presenters.BasePresenter
import ru.test.weather.ui.views.weather.models.WeatherNoDataViewModel
import ru.test.weather.ui.views.weather.models.WeatherViewModelMapper
import javax.inject.Inject
import javax.inject.Named

class WeatherPresenter @Inject constructor(private val interactor: IWeatherInteractor, private val schedulers: ISchedulersProvider,
                                           @Named("IMAGES_URL") private val imagesUrl: String, private val eventBus: IBus,
                                           private val busNotifier: IBusNotifier,
                                           private val locationManager: WeatherLocationManager,
                                           private val locationConfigurator: WeatherLocationConfigurator,
                                           private val errorHandler: IErrorHandler) : BasePresenter<IWeatherView>() {

    private var isRefresh = false
    private val locationCallback: LocationCallback = locationConfigurator.getLocationCallback({
        loadWeather(it.latitude, it.longitude)
        clearLocationCallback()
    }, {
        it?.let {
            if (!it.isLocationAvailable)
                errorHandler.proceed(R.string.weather_no_location_error)
        }

        clearLocationCallback()
    })

    override fun onFirstViewAttach() {
        busNotifier.busEvents.subscribeDispose({ event ->
            when (event) {
                is PermissionResultEvent -> {
                    if (event.requestCode == LOCATION_PERMISSIONS_REQUEST_CODE)
                        if (event.grantResult == PackageManager.PERMISSION_GRANTED)
                            getCurrentLocationAndLoad()
                        else
                            showNoLocationPermissionError()
                }
            }
        })

        when (val weather = interactor.getWeather()) {
            is Optional.Some -> updateData(weather.data)
            is Optional.None -> checkPermissionAndLoad()
        }
    }

    override fun onDestroy() {
        clearLocationCallback()
        super.onDestroy()
    }

    fun onRefreshClick() {
        isRefresh = true
        checkPermissionAndLoad()
    }

    private fun checkPermissionAndLoad() {
        eventBus.notifyBus(CheckPermissionEvent(ACCESS_FINE_LOCATION) { isPermissionAllowed ->
            if (!isPermissionAllowed)
                requestLocationPermission()
            else
                getCurrentLocationAndLoad()
        })
    }

    private fun requestLocationPermission() {
        eventBus.notifyBus(RequestPermissionEvent(ACCESS_FINE_LOCATION, LOCATION_PERMISSIONS_REQUEST_CODE,
                R.string.gps_request_dialog_title, R.string.gps_request_dialog_message,
                R.string.gps_request_dialog_cancel, R.string.gps_request_dialog_ok))
    }

    private fun getCurrentLocationAndLoad() {
        locationManager.getLocation(locationConfigurator.getDefaultLocationRequest(), locationCallback)
    }

    private fun clearLocationCallback() {
        locationManager.removeLocationUpdates(locationCallback)
    }

    private fun loadWeather(latitude: Double, longitude: Double) {
        interactor.loadWeather(WeatherPoint(latitude, longitude), isRefresh)
                .observeOn(schedulers.ui())
                .doOnSubscribe {
                    viewState.changeBlockingProgress(true)
                }
                .doAfterTerminate {
                    isRefresh = false
                    viewState.changeBlockingProgress(false)
                }
                .subscribeDispose({
                    when (it) {
                        is Optional.Some -> updateData(it.data)
                        is Optional.None -> showNoDataError()
                    }
                }, {
                    errorHandler.proceed(it)
                    showNoDataError()
                })
    }

    private fun showNoLocationPermissionError() {
        updateData(null, WeatherNoDataViewModel(R.string.weather_no_location_permission_title,
                R.string.weather_no_location_permission_action) { viewState.openSettings() })
    }

    private fun showNoDataError() {
        updateData(null, WeatherNoDataViewModel(R.string.weather_no_data_title, R.string.weather_no_data_action) { onRefreshClick() })
    }

    private fun updateData(model: Weather?, noDataModel: WeatherNoDataViewModel? = null) {
        model?.let {
            viewState.setData(WeatherViewModelMapper.toViewModel(it, getImageRotateByWindDirection(it.windDirection),
                    getImageUrl(it.iconPath), getTemperatureUnitIconResource(it.temperatureUnit)))
        } ?: viewState.setData(null, noDataModel)
    }

    private fun getImageRotateByWindDirection(windDirection: WindDirection): Float {
        return when (windDirection) {
            WindDirection.Northern -> 180F
            WindDirection.NorthEastern -> 225F
            WindDirection.Eastern -> 270F
            WindDirection.SouthEastern -> 315F
            WindDirection.Southern -> 0F
            WindDirection.SouthWestern -> 45F
            WindDirection.Western -> 90F
            WindDirection.NorthWestern -> 135F
        }
    }

    private fun getImageUrl(iconPath: String, imageSizePostfix: String = "@4x", imageExtension: String = ".png"): String {
        return "$imagesUrl${iconPath}$imageSizePostfix$imageExtension"
    }

    private fun getTemperatureUnitIconResource(temperatureUnit: WeatherTemperatureUnit): Int {
        return when (temperatureUnit) {
            WeatherTemperatureUnit.Kelvin -> R.drawable.ic_temperature_kelvin
            WeatherTemperatureUnit.Fahrenheit -> R.drawable.ic_temperature_fahrenheit
            WeatherTemperatureUnit.Celsius -> R.drawable.ic_temperature_celsius
        }
    }
}