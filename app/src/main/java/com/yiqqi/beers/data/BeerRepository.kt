package com.yiqqi.beers.data

import com.yiqqi.beers.domain.Beer
import kotlinx.coroutines.flow.Flow

interface BeerRepository {


    /**
     * Get beer
     * @param beerId Id of the beer
     */
    suspend fun getBeer(beerId: Long): Flow<Beer?>

    /**
     * Get beers
     */
    suspend fun getBeers(): Flow<List<Beer>>

    /**
     * Set beer availability
     * @param beerId Id of the beer
     * @param available Available or not
     */
    suspend fun setBeerAvailability(beerId: Long, available: Boolean)

}