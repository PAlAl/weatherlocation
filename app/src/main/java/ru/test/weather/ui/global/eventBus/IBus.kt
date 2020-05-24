package ru.test.weather.ui.global.eventBus

interface IBus {
    fun notifyBus(event: BusEvent)
}