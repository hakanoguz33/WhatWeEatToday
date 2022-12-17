package com.oguzapp.whatweeattoday.viewModels

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
        val foodList = findAndGetFoodList(countryName)
        Constants.foodList = foodList!!;
        val recyclerView: RecyclerView = view.findViewById(R.id.fridgeRecyclerView)
        val adapter = FridgeRVAdapter(Constants.foodList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(view.context,2)
    }

    private fun findAndGetFoodList(countryName: String): ArrayList<Food>? {
        for (country in Constants.countriesList)
            if (country.countryName == countryName)
                return country.foodList as ArrayList<Food>
        return null
    }
}