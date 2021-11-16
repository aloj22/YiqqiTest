package com.yiqqi.beers.data.impl

import com.yiqqi.beers.data.BeerRepository
import com.yiqqi.beers.data.source.network.NetworkSource
import com.yiqqi.beers.domain.Beer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BeerRepositoryImpl(
    private val networkSource: NetworkSource
) : BeerRepository {


    override fun getBeer(beerId: Long): Flow<Beer?> = flow {

        emit(networkSource.getBeer(beerId))

    }

    override fun getBeers(): Flow<List<Beer>> = flow {

        //TODO review this
        emit(networkSource.getBeers(1, 10))

    }
}