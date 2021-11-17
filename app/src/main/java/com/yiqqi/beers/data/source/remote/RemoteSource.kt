package com.yiqqi.beers.data.source.remote

import com.yiqqi.beers.domain.Beer

interface RemoteSource {

    suspend fun getBeer(beerId: Long): Beer?

    suspend fun getBeers(page: Int, count: Int): List<Beer>?

}