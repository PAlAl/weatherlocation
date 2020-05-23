package ru.test.weather.di

import dagger.Binds
import dagger.Module
import ru.test.weather.data.repositories.weather.WeatherRepository
import ru.test.weather.domain.repositories.weather.IWeatherRepository

@Module
interface Repositories {

    @Binds
    fun provideWeatherRepository(interactor: WeatherRepository): IWeatherRepository
}