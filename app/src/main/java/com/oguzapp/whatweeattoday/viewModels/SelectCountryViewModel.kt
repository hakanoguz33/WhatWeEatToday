package com.oguzapp.whatweeattoday.viewModels

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oguzapp.whatweeattoday.R
import com.oguzapp.whatweeattoday.adapters.CountriesRVAdapter
import com.oguzapp.whatweeattoday.db.CountryDatabase
import com.oguzapp.whatweeattoday.db.FoodTypeConverters
import com.oguzapp.whatweeattoday.db.model.CountryEntity
import com.oguzapp.whatweeattoday.models.Country
import com.oguzapp.whatweeattoday.network.ApiClient
import com.oguzapp.whatweeattoday.network.Constants
import com.oguzapp.whatweeattoday.network.RetrofitAPI
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import kotlin.concurrent.thread

class SelectCountryViewModel : ViewModel() {
    private lateinit var countriesService: RetrofitAPI
    lateinit var countryList: ArrayList<Country>

    fun downloadCountriesList(context: Context, view: View) {
        countriesService = ApiClient.getClient().create(RetrofitAPI::class.java)
        val post = countriesService.listCountries("id")

        post.enqueue(object : Callback<List<Country>> {
            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                if (response.isSuccessful)
                    countryList = (response.body() as ArrayList<Country>?)!!
                viewModelScope.launch {
                    val countryDatabase = CountryDatabase.getCountryDatabase(context)
                    val file =
                        File(CountryDatabase.getCountryDatabase(context)!!.openHelper.writableDatabase.path)
                    if (System.currentTimeMillis() > file.lastModified()
                            .plus(120000)
                    ) {
                        Constants.countryList = countryList
                        Log.i("Countries List", "Countries List downloaded from API")
                        Toast.makeText(
                            context,
                            "Countrylist downloaded from API",
                            Toast.LENGTH_SHORT
                        ).show()
                        checkAndInsertCountryToDB(context)
                        countryDatabase!!.close()
                    } else {
                        Constants.countryList.clear()
                        for (country in countryDatabase!!.getCountryDao().getAllCountries()) {
                            val country = Country(
                                country.countryId.toString(),
                                country.countryName,
                                country.countryFlagUrl,
                                FoodTypeConverters().fromJSONToFoodList(country.countryFood).foodList
                            )
                            Constants.countryList.add(country)
                        }
                        Log.i("Countries List", "Countries List updated from room database")
                        countryDatabase!!.close()
                        Toast.makeText(
                            context,
                            "Countrylist read from Database",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    setRecyclerViewv(view)
                }
            }
        })
    }

    fun setRecyclerViewv(view: View) {
        setRecyclerView(view)
    }

    private fun setRecyclerView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.countryRV)
        val adapter = CountriesRVAdapter(Constants.countryList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(view.context, 2)
    }

    fun checkAndInsertCountryToDB(context: Context) {
        viewModelScope.launch {
            val foodTypeConverters = FoodTypeConverters()
            val countryDatabase: CountryDatabase = CountryDatabase.getCountryDatabase(context)!!
            countryDatabase.clearAllTables()
            for (country in Constants.countryList) {
                val countryEntity = CountryEntity(
                    country.id.toInt(),
                    country.countryName,
                    country.flagURL,
                    foodTypeConverters.fromFoodToJSON(country.foodList)
                )
                countryDatabase.getCountryDao().addCountry(countryEntity)
            }
            countryDatabase.close()
        }
    }
}