package com.oguzapp.whatweeattoday.db

import android.content.Context
import androidx.room.*
import com.oguzapp.whatweeattoday.db.dao.CountryDao
import com.oguzapp.whatweeattoday.db.model.CountryEntity

@Database(
    version = 2,
    entities = [CountryEntity::class]
)
abstract class CountryDatabase : RoomDatabase() {
    abstract fun getCountryDao(): CountryDao

    companion object {
        private var instance: CountryDatabase? = null

        fun getCountryDatabase(context: Context): CountryDatabase? {
            if (instance == null)
                instance =
                    Room.databaseBuilder(context, CountryDatabase::class.java, "country.db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
            return instance
        }
    }
}