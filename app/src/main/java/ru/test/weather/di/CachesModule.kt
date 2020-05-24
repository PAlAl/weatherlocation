package ru.test.weather.di

import dagger.Binds
import dagger.Module
import ru.test.weather.data.cache.weather.WeatherRealmCache
import ru.test.weather.data.repositories.cache.IWeatherCache

@Module
interface CachesModule {

    @Binds
    fun provideWeatherCache(cache: WeatherRealmCache): IWeatherCache
}