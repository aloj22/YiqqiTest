package com.yiqqi.beers.data.source.local

import com.yiqqi.beers.domain.Beer
import kotlinx.coroutines.flow.Flow

interface LocalSource {


    /**
     * Get beers from local source
     */
    suspend fun getBeers(): Flow<List<Beer>>

    /**
     * Get a beer from local source
     * @param beerId Id of the beer
     */
    suspend fun getBeer(beerId: Long): Flow<Beer?>

    /**
     * Update local beers
     * @param beers Beers to update
     */
    suspend fun updateBeers(beers: List<Beer>)

    /**
     * Update local beer
     * @param beer Beers to update
     */
    suspend fun updateBeer(beer: Beer)

    /**
     * Set beer availability
     * @param beerId Id of the beer
     * @param available Available or not
     */
    suspend fun setBeerAvailability(beerId: Long, available: Boolean)

}