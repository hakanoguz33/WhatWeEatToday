package com.oguzapp.whatweeattoday.db.dao

import androidx.room.*
import com.oguzapp.whatweeattoday.db.model.CountryEntity
import com.oguzapp.whatweeattoday.models.Country

@Dao
interface CountryDao {
    @Insert
    fun addCountry(country: CountryEntity)

    @Delete
    fun delete(country: CountryEntity)

    @Query("SELECT * FROM country_table")
    fun getAllCountries():List<CountryEntity>
}