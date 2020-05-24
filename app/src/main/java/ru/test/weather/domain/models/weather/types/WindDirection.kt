package ru.test.weather.domain.models.weather.types

import ru.test.weather.R
import ru.test.weather.domain.models.DisplayRes

enum class WindDirection {
    @DisplayRes(R.string.enum_wind_direction_northern)
    Northern,

    @DisplayRes(R.string.enum_wind_direction_north_eastern)
    NorthEastern,

    @DisplayRes(R.string.enum_wind_direction_eastern)
    Eastern,

    @DisplayRes(R.string.enum_wind_direction_south_eastern)
    SouthEastern,

    @DisplayRes(R.string.enum_wind_direction_southern)
    Southern,

    @DisplayRes(R.string.enum_wind_direction_south_western)
    SouthWestern,

    @DisplayRes(R.string.enum_wind_direction_western)
    Western,

    @DisplayRes(R.string.enum_wind_direction_north_western)
    NorthWestern;
}