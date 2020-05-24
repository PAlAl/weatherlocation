package ru.test.weather.di

import dagger.Binds
import dagger.Module
import ru.test.weather.data.repositories.weather.WeatherRepository
import ru.test.weather.domain.repositories.weather.IWeatherRepository
import javax.inject.Singleton

@Module
interface Repositories {

    @Binds
    @Singleton
    fun provideWeatherRepository(interactor: WeatherRepository): IWeatherRepository
}