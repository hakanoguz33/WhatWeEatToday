package com.oguzapp.whatweeattoday.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oguzapp.whatweeattoday.R
import com.oguzapp.whatweeattoday.models.Food

class FridgeRVAdapter(private val foodList: ArrayList<Food>) :
    RecyclerView.Adapter<FridgeRVAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val foodImageView: ImageView = view.findViewById(R.id.foodImageView)
        val foodTextView: TextView = view.findViewById(R.id.foodTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentFood = foodList[position]
        Glide.with(holder.itemView).load(currentFood.imageURL).into(holder.foodImageView)
        holder.foodTextView.text = currentFood.name
        holder.foodImageView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("foodName", currentFood.name)
            it.findNavController().navigate(R.id.action_fridgeFragment_to_foodFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    private fun clear(){
        val size = foodList.size
        foodList.clear();
        notifyItemRangeRemoved(0, size)
    }
}