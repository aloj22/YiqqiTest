package com.yiqqi.beers.data.impl

import com.yiqqi.beers.data.BeerRepository
import com.yiqqi.beers.data.source.local.LocalSource
import com.yiqqi.beers.data.source.remote.RemoteSource
import com.yiqqi.beers.domain.Beer
import com.yiqqi.beers.util.launchAsync
import kotlinx.coroutines.flow.Flow



class BeerRepositoryImpl(
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource,
) : BeerRepository {


    override suspend fun getBeer(beerId: Long): Flow<Beer?> {

        // Update beer async
        launchAsync {
            updateBeer(beerId)
        }

        return localSource.getBeer(beerId)

    }


    override suspend fun getBeers(): Flow<List<Beer>> {

        // Update beers async
        launchAsync {
            updateBeers()
        }

        return localSource.getBeers()
    }


    override suspend fun setBeerAvailability(beerId: Long, available: Boolean) {
        localSource.setBeerAvailability(beerId, available)
    }

    private suspend fun updateBeers() {
        remoteSource.getBeers(1, 79)?.let {
            localSource.updateBeers(it)
        }
    }

    private suspend fun updateBeer(beerId: Long) {
        remoteSource.getBeer(beerId)?.let {
            localSource.updateBeer(it)
        }
    }

}