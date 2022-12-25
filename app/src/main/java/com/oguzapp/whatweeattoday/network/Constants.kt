package com.oguzapp.whatweeattoday.network

import com.oguzapp.whatweeattoday.models.Country
import com.oguzapp.whatweeattoday.models.Food
import com.oguzapp.whatweeattoday.models.User

object Constants {
    var baseURL:String = "https://raw.githubusercontent.com"
    var userList:ArrayList<User> = ArrayList()
    var countryList:ArrayList<Country> = ArrayList()
    var foodList:ArrayList<Food> = ArrayList()
}