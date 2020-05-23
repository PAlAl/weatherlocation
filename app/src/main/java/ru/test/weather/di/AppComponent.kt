package ru.test.weather.di

import dagger.Component
import ru.test.weather.ui.views.weather.WeatherFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class, Repositories::class, Interactors::class, InfrastructureModule::class, ContextModule::class])
interface AppComponent {

    fun inject(fragment: WeatherFragment)
}