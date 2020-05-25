package ru.test.weather.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.test.weather.data.models.weather.response.WeatherByCoordinatesResponse

interface IApiService {

    @GET("weather")
    fun getWeatherByCoordinates(@Query("lat") latitude: Double, @Query("lon") longitude: Double,
                                @Query("units") unitsFormat: String,
                                @Query("lang") language: String): Single<WeatherByCoordinatesResponse>
}