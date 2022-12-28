package com.oguzapp.whatweeattoday.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.oguzapp.whatweeattoday.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {
    private val client = OkHttpClient.Builder()
        .readTimeout(Constants.retrofitTimeout, TimeUnit.SECONDS)
        .build()
    private var retrofit: Retrofit? = null
    private var gson: Gson = GsonBuilder()
        .setLenient()
        .create()


    fun getClient(): Retrofit {
        if (retrofit == null)
            retrofit =
                Retrofit.Builder()
                    .baseUrl(Constants.baseURL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

        return retrofit as Retrofit
    }
}