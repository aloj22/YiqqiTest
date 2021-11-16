package com.yiqqi.beers.data

import com.yiqqi.beers.domain.Beer
import kotlinx.coroutines.flow.Flow

interface BeerRepository {

    fun getBeer(beerId: Long): Flow<Beer?>

    fun getBeers(): Flow<List<Beer>>

}