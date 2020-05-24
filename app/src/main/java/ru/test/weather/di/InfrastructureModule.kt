package ru.test.weather.di

import dagger.Binds
import dagger.Module
import ru.test.weather.domain.system.AppSchedulersProvider
import ru.test.weather.domain.system.ISchedulersProvider
import ru.test.weather.domain.system.ISharedPreferencesManager
import ru.test.weather.ui.global.managers.SharedPreferencesManager

@Module
interface InfrastructureModule {
    @Binds
    fun provideSchedulersProvider(schedulersProvider: AppSchedulersProvider): ISchedulersProvider

    @Binds
    fun provideSharedPreferencesManager(sharedPreferencesManager: SharedPreferencesManager): ISharedPreferencesManager
}