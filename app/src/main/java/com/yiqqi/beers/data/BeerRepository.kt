package com.yiqqi.beers.data

import com.yiqqi.beers.domain.Beer
import kotlinx.coroutines.flow.Flow

interface BeerRepository {

    suspend fun getBeer(beerId: Long): Flow<Beer?>

    suspend fun getBeers(): Flow<List<Beer>>

    suspend fun setBeerAvailability(beerId: Long, available: Boolean)

}