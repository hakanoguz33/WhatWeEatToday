package com.oguzapp.whatweeattoday.utils

import com.oguzapp.whatweeattoday.models.Country
import com.oguzapp.whatweeattoday.models.Food
import com.oguzapp.whatweeattoday.models.User

object Constants {
    var baseURL: String = "https://my-json-server.typicode.com/"
    var retrofitTimeout: Long = 10L

    var userList: ArrayList<User> = ArrayList()
    var countryList: ArrayList<Country> = ArrayList()
    var foodList: ArrayList<Food> = ArrayList()

    var TAG_COUNTRY = "countryName"
    var TAG_FOOD = "foodName"
    var DB_NAME = "country.db"

    var localeTurkish = "tr"
    var localeEnglish = "en_GB"
    var english = "English"
    var turkish = "Türkçe"

    var logUserList = "UserList"
    var logUserListDownload = "UserList Downloaded"
    var logCountryList = "Country List"
    var logCountryListFromApi = "Countries List downloaded from API"
    var logCountryListFromRoom = "Countries List updated from room database"
}