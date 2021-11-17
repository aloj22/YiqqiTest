package com.yiqqi.beers.data.source.local.impl.database

import androidx.room.*
import com.yiqqi.beers.data.source.local.impl.model.BeerAvailabilityDB
import com.yiqqi.beers.data.source.local.impl.model.BeerItemAvailability
import com.yiqqi.beers.data.source.local.impl.model.BeerItemDB
import kotlinx.coroutines.flow.Flow


@Dao
interface BeerDAO {



    @Transaction
    @Query("SELECT * FROM ${BeerItemDB.TABLE_NAME} WHERE id LIKE :beerId")
    fun getBeer(beerId: Long): Flow<BeerItemAvailability?>


    @Transaction
    @Query("SELECT * FROM ${BeerItemDB.TABLE_NAME}")
    fun getBeers(): Flow<List<BeerItemAvailability>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBeer(beer: BeerItemDB)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBeers(channels: List<BeerItemDB>)


    @Query("DELETE FROM ${BeerItemDB.TABLE_NAME}")
    suspend fun deleteBeers()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateBeerAvailability(beerAvailabilityDB: BeerAvailabilityDB)

}