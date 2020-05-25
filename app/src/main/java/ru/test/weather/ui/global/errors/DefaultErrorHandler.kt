package ru.test.weather.ui.global.errors

import retrofit2.HttpException
import ru.test.weather.R
import ru.test.weather.ui.global.eventBus.IBus
import ru.test.weather.ui.global.eventBus.systemMessages.SystemMessageEvent
import java.io.IOException
import javax.inject.Inject

class DefaultErrorHandler @Inject constructor(private val evenBus: IBus) : IErrorHandler {

    override fun proceed(error: Throwable) {
        sendToMessageSender(getErrorMessageCustom(error))
    }

    override fun proceed(resId: Int) {
        sendToMessageSender(SystemMessageEvent.SystemMessageResource(resId))
    }

    private fun getErrorMessageCustom(error: Throwable): SystemMessageEvent {
        return when (error) {
            is HttpException, is IOException -> SystemMessageEvent.SystemMessageResource(R.string.error_net_work)
            else -> SystemMessageEvent.SystemMessageResource(R.string.error_other)
        }
    }

    private fun sendToMessageSender(message: SystemMessageEvent) {
        evenBus.notifyBus(message)
    }
}