package ru.test.weather.di

import dagger.Binds
import dagger.Module
import ru.test.weather.domain.system.AppSchedulersProvider
import ru.test.weather.domain.system.ISchedulersProvider
import ru.test.weather.domain.system.ISharedPreferencesManager
import ru.test.weather.ui.global.managers.SharedPreferencesManager
import javax.inject.Singleton

@Module
interface InfrastructureModule {

    @Binds
    @Singleton
    fun provideSchedulersProvider(schedulersProvider: AppSchedulersProvider): ISchedulersProvider

    @Binds
    @Singleton
    fun provideSharedPreferencesManager(sharedPreferencesManager: SharedPreferencesManager): ISharedPreferencesManager
}