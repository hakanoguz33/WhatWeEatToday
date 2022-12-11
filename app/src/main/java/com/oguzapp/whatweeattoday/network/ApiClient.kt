package com.oguzapp.whatweeattoday.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
    private var retrofit: Retrofit? = null
    private var gson: Gson = GsonBuilder()
        .setLenient()
        .create()


    fun getClient(): Retrofit {
        if (retrofit == null)
            retrofit =
                Retrofit.Builder()
                    .baseUrl(Constants.baseURL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

        return retrofit as Retrofit
    }
}