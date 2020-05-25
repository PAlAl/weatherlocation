package ru.test.weather.ui.global.eventBus.systemMessages

import androidx.annotation.StringRes
import ru.test.weather.ui.global.eventBus.BusEvent

sealed class SystemMessageEvent : BusEvent() {
    class SystemMessage(val message: String) : SystemMessageEvent()
    class SystemMessageResource(@StringRes val messageRes: Int) : SystemMessageEvent()
}