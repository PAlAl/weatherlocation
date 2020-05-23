package ru.test.weather.ui.views.weather

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_weather.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.test.weather.R
import ru.test.weather.ui.WeatherApplication
import ru.test.weather.ui.presenters.weather.IWeatherView
import ru.test.weather.ui.presenters.weather.WeatherPresenter
import ru.test.weather.ui.views.BaseFragment
import javax.inject.Inject

class WeatherFragment : BaseFragment(R.layout.fragment_weather), IWeatherView {

    @Inject
    @InjectPresenter
    lateinit var presenter: WeatherPresenter

    @ProvidePresenter
    fun providePresenter(): WeatherPresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as WeatherApplication).getComponent().inject(this)
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