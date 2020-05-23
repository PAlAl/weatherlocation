package ru.test.weather.domain.models.weather.types

enum class WindDirection {
    Northern,
    NorthEastern,
    Eastern,
    SouthEastern,
    Southern,
    SouthWestern,
    Western,
    NorthWestern;

    companion object {
        fun getWindDirectionByDegree(degree: Float): WindDirection {
            return when {
                degree > 337.5 -> Northern
                degree > 292.5 -> NorthWestern
                degree > 247.5 -> Western
                degree > 202.5 -> SouthWestern
                degree > 157.5 -> Southern
                degree > 122.5 -> SouthEastern
                degree > 67.5 -> Eastern
                degree > 22.5 -> NorthEastern
                else -> Northern
            }
        }
    }
}