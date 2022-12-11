package com.oguzapp.whatweeattoday.viewModels

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oguzapp.whatweeattoday.R
import com.oguzapp.whatweeattoday.adapters.CountriesRVAdapter
import com.oguzapp.whatweeattoday.models.Countries
import com.oguzapp.whatweeattoday.models.User
import com.oguzapp.whatweeattoday.network.ApiClient
import com.oguzapp.whatweeattoday.network.Constants
import com.oguzapp.whatweeattoday.network.RetrofitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectCountryViewModel : ViewModel() {
    private lateinit var countriesService: RetrofitAPI
    lateinit var countriesList: ArrayList<Countries>

    fun downloadCountriesList(context: Context,view: View){
        countriesService = ApiClient.getClient().create(RetrofitAPI::class.java)
        val post = countriesService.listCountries("id")

        post.enqueue(object : Callback<List<Countries>> {
            override fun onFailure(call: Call<List<Countries>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Countries>>, response: Response<List<Countries>>) {
                if (response.isSuccessful)
                    countriesList = (response.body() as ArrayList<Countries>?)!!
                Constants.countriesList = countriesList
                Log.i("countrieslist indi","countrieslist indiiiii")
                setRecyclerView(view)
            }
        })
    }

    fun setRecyclerView(view:View){
        val recyclerView: RecyclerView = view.findViewById(R.id.countryRV)
        val adapter = CountriesRVAdapter(Constants.countriesList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(view.context,2)
    }
}