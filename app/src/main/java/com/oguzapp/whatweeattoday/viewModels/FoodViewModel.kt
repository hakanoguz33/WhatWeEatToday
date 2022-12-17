package com.oguzapp.whatweeattoday.viewModels

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.oguzapp.whatweeattoday.R
import com.oguzapp.whatweeattoday.models.Food
import com.oguzapp.whatweeattoday.network.Constants

class FoodViewModel : ViewModel() {
    fun setScreen(view: View, foodName: String) {
        val foodTextView: TextView = view.findViewById(R.id.foodScreenTextView)
        val foodImageView: ImageView = view.findViewById(R.id.foodScreenImageView)
        val food = findAndSetFood(foodName)

        if (food.name.isEmpty()) {
            Toast.makeText(view.context, "Yemek Tarifi Bulunamadı", Toast.LENGTH_SHORT).show()
            view.findNavController().navigate(R.id.action_foodFragment_to_fridgeFragment)
        } else {
            var material = food.materials
            material = material.replace(",",System.getProperty("line.separator"))
            foodTextView.text = material
            Glide.with(view).load(food.imageUrl).into(foodImageView)
        }
    }

    private fun findAndSetFood(foodName: String): Food {
        for (food in Constants.foodList)
            if (foodName == food.name)
                return food
        return Food("", "", "")
    }
}