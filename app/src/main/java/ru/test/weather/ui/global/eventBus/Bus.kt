package ru.test.weather.ui.global.eventBus

import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Bus @Inject constructor() : IBusNotifier, IBus {

    override val busEvents: PublishSubject<BusEvent> = PublishSubject.create()

    override fun notifyBus(event: BusEvent) {
        busEvents.onNext(event)
    }
}