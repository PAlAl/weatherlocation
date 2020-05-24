package ru.test.weather.ui.views.weather

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_weather.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.test.weather.R
import ru.test.weather.common.getDisplayResource
import ru.test.weather.ui.WeatherApplication
import ru.test.weather.ui.global.images.ImageLoader
import ru.test.weather.ui.presenters.weather.IWeatherView
import ru.test.weather.ui.presenters.weather.WeatherPresenter
import ru.test.weather.ui.views.BaseFragment
import ru.test.weather.ui.views.weather.models.WeatherViewModel
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

    override fun setData(model: WeatherViewModel) {
        ImageLoader.simpleLoad(weather_image, model.imageUrl, weather_image)
        weather_temperature_value.text = model.temperature
        weather_temperature_unit.setImageResource(model.temperatureUnitIcon)
        weather_wind_value.text = model.windSpeed
        weather_wind_unit.text = model.windUnit.getDisplayResource(requireContext())
        weather_wind_direction.text = model.windDirection.getDisplayResource(requireContext())
        weather_wind_direction_image.rotation = model.windDirectionImageRotate
    }

    override fun changeBlockingProgress(isShow: Boolean) {
        showProgressDialog(isShow)
    }
}