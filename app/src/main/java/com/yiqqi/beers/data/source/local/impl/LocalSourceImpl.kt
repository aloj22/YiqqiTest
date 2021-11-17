package com.yiqqi.beers.data.source.local.impl

import com.yiqqi.beers.data.source.local.LocalSource
import com.yiqqi.beers.data.source.local.impl.database.BeerDAO
import com.yiqqi.beers.data.source.local.impl.model.BeerAvailabilityDB
import com.yiqqi.beers.domain.Beer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalSourceImpl(
    private val beerDAO: BeerDAO,
    private val localSourceMapper: LocalSourceMapper
) : LocalSource {


    override fun getBeers() = beerDAO.getBeers().map(localSourceMapper::beersFromBeerItemDBList)


    override fun getBeer(beerId: Long): Flow<Beer?> = beerDAO.getBeer(beerId).map {
        if (it != null) { localSourceMapper.beerFromBeerItemDB(it) } else null
    }


    override suspend fun updateBeers(beers: List<Beer>) {
        beerDAO.updateBeers(beers.map(localSourceMapper::beerItemDBFromBeer))
    }


    override suspend fun updateBeer(beer: Beer) {
        beerDAO.insertBeer(localSourceMapper.beerItemDBFromBeer(beer))
    }


    override suspend fun setBeerAvailability(beerId: Long, available: Boolean) {
        beerDAO.updateBeerAvailability(BeerAvailabilityDB(beerId, available))
    }

}