package ru.test.weather.ui.global.eventBus

import io.reactivex.subjects.PublishSubject

interface IBusNotifier {
    val busEvents: PublishSubject<BusEvent>
}