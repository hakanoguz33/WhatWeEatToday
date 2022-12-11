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
import com.oguzapp.whatweeattoday.models.Countries

class CountriesRVAdapter(private val countriesList: ArrayList<Countries>) :
    RecyclerView.Adapter<CountriesRVAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val countryFlag: ImageView = view.findViewById(R.id.countryFlag)
        val countryName: TextView = view.findViewById(R.id.countryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_countries, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView).load(countriesList[position].flagUrl).into(holder.countryFlag)
        holder.countryName.text = countriesList[position].countryName
        holder.countryFlag.setOnClickListener {
            val bundle:Bundle = Bundle()
            bundle.putString("countryName",countriesList[position].countryName)
            it.findNavController().navigate(R.id.action_selectCountryFragment_to_fridgeFragment,bundle)
        }
    }

    override fun getItemCount(): Int {
        return countriesList.size
    }
}