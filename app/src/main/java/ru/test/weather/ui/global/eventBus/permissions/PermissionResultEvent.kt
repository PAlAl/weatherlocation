package ru.test.weather.ui.global.eventBus.permissions

import ru.test.weather.ui.global.eventBus.BusEvent

class PermissionResultEvent(val requestCode: Int,
                            val grantResult: Int) : BusEvent() {
}