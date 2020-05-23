package ru.test.weather.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.test.weather.R
import ru.test.weather.ui.views.weather.WeatherFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .add(R.id.container, WeatherFragment())
                .commit()
    }
}
