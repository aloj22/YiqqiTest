package com.yiqqi.beers.data.source.remote.impl

import com.yiqqi.beers.data.source.remote.RemoteSource
import com.yiqqi.beers.util.dispatcherprovider.DispatcherProvider
import kotlinx.coroutines.withContext



class RemoteSourceImpl(
    private val apiService: ApiService,
    private val apiMapper: ApiMapper,
    private val dispatcherProvider: DispatcherProvider
) : RemoteSource {


    override suspend fun getBeer(beerId: Long) = withContext(dispatcherProvider.ioDispatcher) {
        try {
            apiMapper.beerFromBeerResponse(apiService.getBeer(beerId).first())
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


    override suspend fun getBeers(page: Int, count: Int) = withContext(dispatcherProvider.ioDispatcher) {
        try {
            apiService.getBeers(page, count).map(apiMapper::beerFromBeerResponse)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

}