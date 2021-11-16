package com.yiqqi.beers.data.source.network.impl

import com.yiqqi.beers.data.source.network.NetworkSource
import com.yiqqi.beers.util.dispatcherprovider.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkSourceImpl(
    private val apiService: ApiService,
    private val apiMapper: ApiMapper,
    private val dispatcherProvider: DispatcherProvider
) : NetworkSource {


    override suspend fun getBeers(page: Int, count: Int) = withContext(dispatcherProvider.ioDispatcher) {
        try {
            apiService.getBeers(page, count).map(apiMapper::beerFromBeerResponse)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

}