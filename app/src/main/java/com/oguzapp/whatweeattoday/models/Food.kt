package com.oguzapp.whatweeattoday.models

import com.google.gson.annotations.SerializedName

data class Food (
    val name: String,
    @SerializedName("imageUrl")
    val imageURL: String,
    val materials: String
)