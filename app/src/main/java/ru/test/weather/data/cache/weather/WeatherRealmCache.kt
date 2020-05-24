package ru.test.weather.data.cache.weather

import io.realm.Realm
import org.joda.time.DateTime
import ru.test.weather.data.cache.SharedPreferencesKeys.LAST_WEATHER_LOAD_TIME_MILLS_KEY
import ru.test.weather.data.models.weather.db.WeatherDb
import ru.test.weather.data.repositories.cache.IWeatherCache
import ru.test.weather.domain.system.ISharedPreferencesManager
import javax.inject.Inject

class WeatherRealmCache @Inject constructor(private val sharedPreferencesManager: ISharedPreferencesManager) : IWeatherCache {

    override fun getWeatherDb(): WeatherDb? {
        var model: WeatherDb? = null

        Realm.getDefaultInstance().use { realm ->
            val results = realm.where(WeatherDb::class.java).findFirst()
            if (results != null)
                model = realm.copyFromRealm(results)
        }

        return model
    }

    override fun save(weather: WeatherDb) {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction {
                realm.insertOrUpdate(weather)
            }
        }
    }

    override fun getLastLoadDateTime(): DateTime? {
        val loadMills = sharedPreferencesManager.getLong(LAST_WEATHER_LOAD_TIME_MILLS_KEY)
        return if (loadMills == 0L) null else DateTime(loadMills)
    }

    override fun setLastLoadDateTime(date: DateTime) {
        sharedPreferencesManager.putLong(LAST_WEATHER_LOAD_TIME_MILLS_KEY, date.millis)
    }
}