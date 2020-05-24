package ru.test.weather.di

import dagger.Binds
import dagger.Module
import ru.test.weather.domain.interactors.weather.IWeatherInteractor
import ru.test.weather.domain.interactors.weather.IWeatherInteractorConfig
import ru.test.weather.domain.interactors.weather.WeatherInteractor
import ru.test.weather.domain.interactors.weather.WeatherInteractorConfig
import javax.inject.Singleton

@Module
interface Interactors {

    @Binds
    @Singleton
    fun provideWeatherInteractorConfig(config: WeatherInteractorConfig): IWeatherInteractorConfig

    @Binds
    @Singleton
    fun provideWeatherInteractor(interactor: WeatherInteractor): IWeatherInteractor
}