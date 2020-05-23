package ru.test.weather.ui

import android.app.Application
import ru.test.weather.di.AppComponent
import ru.test.weather.di.DaggerAppComponent

class WeatherApplication : Application() {

    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().build()
    }

    fun getComponent(): AppComponent {
        return component
    }
}