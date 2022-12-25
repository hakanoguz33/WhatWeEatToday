package com.oguzapp.whatweeattoday.models

import com.google.gson.annotations.SerializedName

data class Country (
    val id: String,
    @SerializedName("name")
    val countryName: String,
    @SerializedName("flagUrl")
    val flagURL: String,
    @SerializedName("food")
    val foodList: ArrayList<Food>
)