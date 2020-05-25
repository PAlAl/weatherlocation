package ru.test.weather.ui

import androidx.multidex.MultiDexApplication
import io.realm.Realm
import io.realm.RealmConfiguration
import ru.test.weather.di.AppComponent
import ru.test.weather.di.ContextModule
import ru.test.weather.di.DaggerAppComponent

class WeatherApplication : MultiDexApplication() {

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