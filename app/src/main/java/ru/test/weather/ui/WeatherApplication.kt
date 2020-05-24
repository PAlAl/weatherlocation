package ru.test.weather.ui

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.rx.RealmObservableFactory
import ru.test.weather.di.AppComponent
import ru.test.weather.di.ContextModule
import ru.test.weather.di.DaggerAppComponent

class WeatherApplication : Application() {

    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        initRealm()
        component = DaggerAppComponent.builder().contextModule(ContextModule(this)).build()
    }

    fun getComponent(): AppComponent {
        return component
    }

    fun initRealm() {
        Realm.init(this)
        val configuration = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(configuration)
    }
}