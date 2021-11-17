package com.yiqqi.beers.data.source.local

import com.yiqqi.beers.domain.Beer
import kotlinx.coroutines.flow.Flow

interface LocalSource {


    suspend fun getBeers(): Flow<List<Beer>>

    suspend fun getBeer(beerId: Long): Flow<Beer?>

    suspend fun updateBeers(beers: List<Beer>)

    suspend fun updateBeer(beer: Beer)

    suspend fun setBeerAvailability(beerId: Long, available: Boolean)

}