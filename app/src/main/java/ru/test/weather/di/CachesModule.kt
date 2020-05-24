package ru.test.weather.di

import dagger.Binds
import dagger.Module
import ru.test.weather.data.cache.weather.WeatherRealmCache
import ru.test.weather.data.repositories.cache.IWeatherCache
import javax.inject.Singleton

@Module
interface CachesModule {

    @Binds
    @Singleton
    fun provideWeatherCache(cache: WeatherRealmCache): IWeatherCache
}