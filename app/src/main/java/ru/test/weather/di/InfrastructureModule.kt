package ru.test.weather.di

import dagger.Binds
import dagger.Module
import ru.test.weather.domain.system.AppSchedulersProvider
import ru.test.weather.domain.system.ISchedulersProvider

@Module
interface InfrastructureModule {
    @Binds
    fun provideSchedulersProvider(schedulersProvider: AppSchedulersProvider): ISchedulersProvider
}