package com.android.testapp.di.module

import com.android.testapp.data.webservice.RestService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class ApplicationModule {

    @Module
    companion object {
        const val APPLICATION_CONTEXT = "application.context"
        const val SERVER_KEY =  "fd78102346e84058b2e52185ca2c9606"
        const val BASE_URL =  "https://newsapi.org/v2/"

        @JvmStatic
        @Singleton
        @Provides
        fun provideRetrofit(): Retrofit {
            val httpClient = OkHttpClient.Builder()

            val client = httpClient.addInterceptor({ chain ->
                val request = chain.request().newBuilder().build()
                chain.proceed(request)
            }).readTimeout(40, TimeUnit.SECONDS).build()
            val gson = GsonBuilder()
                .setLenient()
                .create()

            return Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideRetrofitService(retrofit: Retrofit): RestService {
            return retrofit.create<RestService>(RestService::class.java)
        }

    }
}