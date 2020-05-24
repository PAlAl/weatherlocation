package ru.test.weather.ui.views.weather

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
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
                    presenter.onRefreshClick()
                }
            }
            true
        }

        weather_no_data_refresh_title.apply {
            val transitionLabel = SpannableString(weather_no_data_refresh_title.text)
            transitionLabel.setSpan(UnderlineSpan(), 0, transitionLabel.length, 0)
            this.text = transitionLabel
        }

        weather_no_data_refresh_title.setOnClickListener {
            presenter.onRefreshClick()
        }
    }

    override fun setData(model: WeatherViewModel?) {
        if (model != null) {
            weather_no_data_group.visibility = View.GONE
            weather_data_container.visibility = View.VISIBLE
            ImageLoader.simpleLoad(weather_image, model.imageUrl, weather_image)
            weather_temperature_value.text = model.temperature
            weather_temperature_unit.setImageResource(model.temperatureUnitIcon)
            weather_wind_value.text = model.windSpeed
            weather_wind_unit.text = model.windUnit.getDisplayResource(requireContext())
            weather_wind_direction.text = model.windDirection.getDisplayResource(requireContext())
            weather_wind_direction_image.rotation = model.windDirectionImageRotate
        } else {
            weather_data_container.visibility = View.GONE
            weather_no_data_group.visibility = View.VISIBLE
        }
    }

    override fun changeBlockingProgress(isShow: Boolean) {
        showProgressDialog(isShow)
    }
}