package com.example.mvvmtest.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This is a singleton.
 */
object API {
    val cocktalApi:Service by lazy{
        initRetrofit()
    }

    private fun initRetrofit(): Service{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Service::class.java) // we create a Service
    }
}