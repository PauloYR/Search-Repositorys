package com.pauloyr.searchrepository.data.network

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Service {
     fun <Api> buildApi(
        api: Class<Api>,
        context: Context,
        baseUrl: String
    ): Api {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}