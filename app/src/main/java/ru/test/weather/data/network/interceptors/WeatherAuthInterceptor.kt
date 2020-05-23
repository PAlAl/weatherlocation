package ru.test.weather.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class WeatherAuthInterceptor(private val privateKye: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url().newBuilder()
                .addQueryParameter("appid", privateKye)
                .build()

        val request = chain.request().newBuilder().url(url).build()

        return chain.proceed(request)
    }
}