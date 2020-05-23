package ru.test.weather.di

import dagger.Binds
import dagger.Module
import ru.test.weather.domain.interactors.weather.IWeatherInteractor
import ru.test.weather.domain.interactors.weather.WeatherInteractor

@Module
interface Interactors {

    @Binds
    fun provideWeatherInteractor(interactor: WeatherInteractor): IWeatherInteractor
}