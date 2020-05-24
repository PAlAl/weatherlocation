package ru.test.weather.di

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.test.weather.BuildConfig
import ru.test.weather.data.network.IApiService
import ru.test.weather.data.network.interceptors.WeatherAuthInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetModule {

    @Singleton
    @Provides
    fun provideApiInterface(retrofit: Retrofit): IApiService =
            retrofit.create(IApiService::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson:Gson, @Named("SERVER_URL") url: String): Retrofit =
            Retrofit.Builder()
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(url)
                    .build()

    @Singleton
    @Provides
    fun provideGson(): Gson =
            GsonBuilder()
                    .serializeNulls()
                    .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                    .create()

    @Singleton
    @Provides
    fun provideOkHttp(context: Context): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)

        okHttpBuilder.addInterceptor(WeatherAuthInterceptor(BuildConfig.API_KEY))

        if (BuildConfig.CHUCK_ENABLE)
            okHttpBuilder.addInterceptor(ChuckInterceptor(context))

        return okHttpBuilder.build()
    }

    @Provides
    @Named("SERVER_URL")
    fun provideServerUrl(): String = BuildConfig.SERVER_URL

    @Provides
    @Named("IMAGES_URL")
    fun provideImagesUrl(): String = BuildConfig.IMAGES_URL
}