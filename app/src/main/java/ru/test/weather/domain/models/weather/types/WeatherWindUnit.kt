package ru.test.weather.domain.models.weather.types

import ru.test.weather.R
import ru.test.weather.domain.models.DisplayRes

enum class WeatherWindUnit {
    @DisplayRes(R.string.enum_wind_unit_meter_by_second)
    MeterBySecond,

    @DisplayRes(R.string.enum_wind_unit_miles_by_hour)
    MilesByHour;
}