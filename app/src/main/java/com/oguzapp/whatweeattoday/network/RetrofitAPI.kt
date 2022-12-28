package com.oguzapp.whatweeattoday.network

import com.oguzapp.whatweeattoday.models.Country
import com.oguzapp.whatweeattoday.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface RetrofitAPI {
    @GET("hakanoguz33/jsonServerDemo/users")
    fun listUser(): Call<List<User>>

    @GET("hakanoguz33/jsonServerDemo/country")
    fun listCountries(): Call<List<Country>>

    @POST("hakanoguz33/jsonServerDemo/users")
    fun createUser(@Body user: List<User>?): Call<List<User>>
}