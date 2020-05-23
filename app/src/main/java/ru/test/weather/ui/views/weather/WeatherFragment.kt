package ru.test.weather.ui.views.weather

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_weather.*
import ru.test.weather.R
import ru.test.weather.ui.views.BaseFragment

class WeatherFragment : BaseFragment(R.layout.fragment_weather) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.inflateMenu(R.menu.weather_menu)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.refresh -> {
                }
            }
            true
        }
    }
}