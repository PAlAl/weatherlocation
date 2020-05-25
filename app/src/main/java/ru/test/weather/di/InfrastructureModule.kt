package ru.test.weather.di

import dagger.Binds
import dagger.Module
import ru.test.weather.domain.system.AppSchedulersProvider
import ru.test.weather.domain.system.ISchedulersProvider
import ru.test.weather.domain.system.ISharedPreferencesManager
import ru.test.weather.ui.global.errors.DefaultErrorHandler
import ru.test.weather.ui.global.errors.IErrorHandler
import ru.test.weather.ui.global.eventBus.Bus
import ru.test.weather.ui.global.eventBus.IBus
import ru.test.weather.ui.global.eventBus.IBusNotifier
import ru.test.weather.ui.global.sharedPreferences.SharedPreferencesManager
import javax.inject.Singleton

@Module
interface InfrastructureModule {

    @Binds
    @Singleton
    fun provideSchedulersProvider(schedulersProvider: AppSchedulersProvider): ISchedulersProvider

    @Binds
    @Singleton
    fun provideSharedPreferencesManager(sharedPreferencesManager: SharedPreferencesManager): ISharedPreferencesManager

    @Binds
    fun provideBusNotifier(bus: Bus): IBusNotifier

    @Binds
    fun provideBus(bus: Bus): IBus

    @Binds
    fun provideErrorHandler(errorHandler: DefaultErrorHandler): IErrorHandler
}