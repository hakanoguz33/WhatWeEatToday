package com.oguzapp.whatweeattoday.viewModels

import android.R.attr.data
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oguzapp.whatweeattoday.R
import com.oguzapp.whatweeattoday.adapters.FridgeRVAdapter
import com.oguzapp.whatweeattoday.models.Food
import com.oguzapp.whatweeattoday.network.Constants


class FridgeViewModel : ViewModel() {
    fun setRecyclerView(view: View,countryName: String){
        val food = findAndGetFoodList(countryName)
        Constants.foodList.add(food!!)
        val recyclerView: RecyclerView = view.findViewById(R.id.fridgeRecyclerView)
        val adapter = FridgeRVAdapter(Constants.foodList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(view.context,2)
    }

    private fun findAndGetFoodList(countryName: String): Food? {
        for (country in Constants.countriesList)
            if (country.countryName == countryName)
                return country.food
        return null
    }
}