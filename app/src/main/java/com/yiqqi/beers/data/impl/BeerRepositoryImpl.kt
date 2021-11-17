package com.yiqqi.beers.data.impl

import com.yiqqi.beers.data.BeerRepository
import com.yiqqi.beers.data.source.remote.RemoteSource
import com.yiqqi.beers.domain.Beer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BeerRepositoryImpl(
    private val remoteSource: RemoteSource
) : BeerRepository {


    override fun getBeer(beerId: Long): Flow<Beer?> = flow {

        emit(remoteSource.getBeer(beerId))

    }

    override fun getBeers(): Flow<List<Beer>> = flow {

        //TODO review this
        emit(remoteSource.getBeers(1, 10))

    }
}