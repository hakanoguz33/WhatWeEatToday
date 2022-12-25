package com.oguzapp.whatweeattoday.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oguzapp.whatweeattoday.models.Food

@Entity(tableName = "country_table")
data class CountryEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "countryId")
    var countryId: Int = 0,

    @ColumnInfo(name = "countryName")
    var countryName: String,

    @ColumnInfo(name = "flagUrl")
    var countryFlagUrl: String,

    @ColumnInfo(name = "food")
    var countryFood: String
)