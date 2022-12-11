package com.oguzapp.whatweeattoday.models

import com.google.gson.annotations.SerializedName

data class Countries(val id:String, @SerializedName("name")val countryName:String, val flagUrl:String, val food: Food) {
}