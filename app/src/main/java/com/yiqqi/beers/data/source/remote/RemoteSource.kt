package com.yiqqi.beers.data.source.remote

import com.yiqqi.beers.domain.Beer

interface RemoteSource {


    /**
     * Get beer from remote source
     * @param beerId Id of the beer
     */
    suspend fun getBeer(beerId: Long): Beer?

    /**
     * Get a list of beers from remote source
     * @param page Page index
     * @param count Items to get
     */
    suspend fun getBeers(page: Int, count: Int): List<Beer>?

}