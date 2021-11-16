package com.yiqqi.beers.data.source.network

import com.yiqqi.beers.domain.Beer

interface NetworkSource {

    suspend fun getBeers(page: Int, count: Int): List<Beer>

}