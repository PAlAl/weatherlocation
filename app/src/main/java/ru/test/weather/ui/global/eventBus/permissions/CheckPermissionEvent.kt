package ru.test.weather.ui.global.eventBus.permissions

import ru.test.weather.ui.global.eventBus.BusEvent

class CheckPermissionEvent(val permission: String, val callback: (Boolean) -> Unit) : BusEvent() {
}