package ru.test.weather.ui.global.eventBus.permissions

import androidx.annotation.StringRes
import ru.test.weather.ui.global.eventBus.BusEvent

class RequestPermissionEvent(val permission: String, val requestCode: Int,
                             @StringRes val explanationDialogTitle: Int, @StringRes val explanationDialogMessage: Int,
                             @StringRes val explanationDialogCancelTitle: Int, @StringRes val explanationDialogOkTitle: Int) : BusEvent() {
}