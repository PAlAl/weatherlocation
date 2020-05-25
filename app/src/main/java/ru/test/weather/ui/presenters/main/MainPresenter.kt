package ru.test.weather.ui.presenters.main

import ru.test.weather.ui.global.eventBus.IBus
import ru.test.weather.ui.global.eventBus.IBusNotifier
import ru.test.weather.ui.global.eventBus.permissions.CheckPermissionEvent
import ru.test.weather.ui.global.eventBus.permissions.PermissionResultEvent
import ru.test.weather.ui.global.eventBus.permissions.RequestPermissionEvent
import ru.test.weather.ui.presenters.BasePresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(private val eventBus: IBus, private val busNotifier: IBusNotifier) : BasePresenter<IMainView>() {

    override fun onFirstViewAttach() {
        busNotifier.busEvents.subscribeDispose({ event ->
            when (event) {
                is CheckPermissionEvent -> viewState.checkPermission(event.permission, event.callback)
                is RequestPermissionEvent -> viewState.requestPermission(event.permission, event.requestCode,
                        event.explanationDialogTitle, event.explanationDialogMessage, event.explanationDialogCancelTitle, event.explanationDialogOkTitle)
            }
        })

        viewState.checkGoogleApiAvailability()
    }

    fun onRequestPermissionsResult(requestCode: Int, grantResult: Int) {
        eventBus.notifyBus(PermissionResultEvent(requestCode, grantResult))
    }

    fun onCheckGoogleApiAvailabilitySuccess() {
        viewState.openWeatherScreen()
    }
}