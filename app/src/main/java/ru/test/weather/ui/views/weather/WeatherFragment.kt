package ru.test.weather.ui.views.weather

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
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
import ru.test.weather.ui.views.weather.models.WeatherNoDataViewModel
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
    }

    override fun setData(model: WeatherViewModel?, noDataModel: WeatherNoDataViewModel?) {
        if (model != null) {
            weather_no_data_title.visibility = View.GONE
            weather_no_data_refresh_title.visibility = View.GONE
            weather_data_container.visibility = View.VISIBLE
            setDataContainerInfo(model)
        } else {
            weather_data_container.visibility = View.GONE
            weather_no_data_title.visibility = View.VISIBLE
            weather_no_data_refresh_title.visibility = View.VISIBLE
            noDataModel?.let {
                setNoDataContainerInfo(noDataModel)
            }
        }
    }

    private fun setDataContainerInfo(model: WeatherViewModel) {
        ImageLoader.simpleLoad(weather_image, model.imageUrl, weather_image)
        weather_temperature_value.text = model.temperature
        weather_temperature_unit.setImageResource(model.temperatureUnitIcon)
        weather_wind_value.text = model.windSpeed
        weather_wind_unit.text = model.windUnit.getDisplayResource(requireContext())
        weather_wind_direction.text = model.windDirection.getDisplayResource(requireContext())
        weather_wind_direction_image.rotation = model.windDirectionImageRotate
    }

    private fun setNoDataContainerInfo(noDataModel: WeatherNoDataViewModel) {
        weather_no_data_title.text = getString(noDataModel.message)
        weather_no_data_refresh_title.apply {
            val label = SpannableString(getString(noDataModel.actionMessage))
            label.setSpan(UnderlineSpan(), 0, label.length, 0)
            this.text = label
        }

        weather_no_data_refresh_title.setOnClickListener {
            noDataModel.action()
        }
    }

    override fun changeBlockingProgress(isShow: Boolean) {
        showProgressDialog(isShow)
    }

    override fun openSettings() {
        requireContext().startActivity(Intent().apply {
            action = ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", requireActivity().packageName, null)
        })
    }
}