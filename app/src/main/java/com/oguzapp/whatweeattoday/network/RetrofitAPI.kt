package com.oguzapp.whatweeattoday.network

import com.oguzapp.whatweeattoday.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {
    //@GET("b/1MWR")
    @GET("hakanoguz33/WhatWeEatToday/main/users.json")
    fun listUser(@Query("userId") userId: String): Call<List<User>>
}