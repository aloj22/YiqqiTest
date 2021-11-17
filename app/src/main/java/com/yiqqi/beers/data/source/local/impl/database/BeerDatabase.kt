package com.yiqqi.beers.data.source.local.impl.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yiqqi.beers.data.source.local.impl.model.BeerAvailabilityDB
import com.yiqqi.beers.data.source.local.impl.model.BeerItemDB

@Database(entities = [BeerItemDB::class, BeerAvailabilityDB::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BeerDatabase : RoomDatabase() {


    companion object {

        private const val DATABASE_NAME = "beers_database"

        fun getInstance(context: Context) =
            Room.databaseBuilder(context, BeerDatabase::class.java, DATABASE_NAME)
                .build()
    }


    abstract fun beerDAO(): BeerDAO

}