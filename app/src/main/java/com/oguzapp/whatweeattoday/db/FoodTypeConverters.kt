package com.oguzapp.whatweeattoday.db

import com.google.gson.Gson
import com.oguzapp.whatweeattoday.db.model.FoodListModel
import com.oguzapp.whatweeattoday.models.Food

class FoodTypeConverters {
    fun fromFoodToJSON(foodList: ArrayList<Food>): String {
        val foodListModel = FoodListModel(foodList)
        return Gson().toJson(foodListModel)
    }

    fun fromJSONToFoodList(json: String): FoodListModel {
        return Gson().fromJson(json, FoodListModel::class.java)
    }
}