package com.yiqqi.beers.data.source.local.impl

import com.yiqqi.beers.data.source.local.LocalSource
import com.yiqqi.beers.data.source.local.impl.database.BeerDAO
import com.yiqqi.beers.data.source.local.impl.model.BeerAvailabilityDB
import com.yiqqi.beers.domain.Beer
import com.yiqqi.beers.util.dispatcherprovider.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LocalSourceImpl(
    private val beerDAO: BeerDAO,
    private val localSourceMapper: LocalSourceMapper,
    private val dispatcherProvider: DispatcherProvider,
) : LocalSource {


    override suspend fun getBeers() = withContext(dispatcherProvider.cpuDispatcher) {
        beerDAO.getBeers().map(localSourceMapper::beersFromBeerItemDBList)
    }


    override suspend fun getBeer(beerId: Long): Flow<Beer?> = beerDAO.getBeer(beerId).map {
        if (it != null) { localSourceMapper.beerFromBeerItemDB(it) } else null
    }


    override suspend fun updateBeers(beers: List<Beer>) = withContext(dispatcherProvider.cpuDispatcher) {
        beerDAO.insertBeers(localSourceMapper.beersItemDBFromBeerList(beers))
    }


    override suspend fun updateBeer(beer: Beer) = withContext(dispatcherProvider.cpuDispatcher) {
        beerDAO.insertBeer(localSourceMapper.beerItemDBFromBeer(beer))
    }


    override suspend fun setBeerAvailability(beerId: Long, available: Boolean) = withContext(dispatcherProvider.cpuDispatcher) {
        beerDAO.updateBeerAvailability(BeerAvailabilityDB(beerId, available))
    }

}