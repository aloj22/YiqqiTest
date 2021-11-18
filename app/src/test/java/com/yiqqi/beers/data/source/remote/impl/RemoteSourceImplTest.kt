package com.yiqqi.beers.data.source.remote.impl

import com.nhaarman.mockitokotlin2.*
import com.yiqqi.beers.DispatcherProviderTestImpl
import com.yiqqi.beers.LiveDataAndCoroutineDispatcherRule
import com.yiqqi.beers.data.source.remote.model.BeerResponse
import com.yiqqi.beers.domain.Beer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RemoteSourceImplTest {


    @get:Rule
    val liveDataAndCoroutineDispatcher = LiveDataAndCoroutineDispatcherRule()

    private val apiService = mock<ApiService>()
    private val apiMapper = mock<ApiMapper>()

    private val remoteSourceImpl = RemoteSourceImpl(apiService, apiMapper, DispatcherProviderTestImpl())


    @Test
    fun `Should get beer`() = runBlockingTest {

        val beer = mock<Beer>()
        val beerApiItem = mock<BeerResponse>()
        val beerAPI = listOf(beerApiItem)

        whenever(apiService.getBeer(any())).doReturn(beerAPI)
        apiMapper.stub {
            on { beerFromBeerResponse(any()) } doReturn beer
        }

        val beerId = 6556L

        assertEquals(beer, remoteSourceImpl.getBeer(beerId))

        verify(apiService).getBeer(eq(beerId))
        verify(apiMapper).beerFromBeerResponse(eq(beerApiItem))

    }

    @Test
    fun `Should get beers`() = runBlockingTest {

        val beerList = listOf<Beer>(mock(), mock(), mock())
        val beersAPI = listOf<BeerResponse>(mock(), mock(), mock())

        whenever(apiService.getBeers(any(), any())).doReturn(beersAPI)
        apiMapper.stub {
            on { beersFromBeerResponse(any()) } doReturn beerList
        }

        val page = 34
        val pageSize = 32

        assertEquals(beerList, remoteSourceImpl.getBeers(page, pageSize))

        verify(apiService).getBeers(eq(page), eq(pageSize))
        verify(apiMapper).beersFromBeerResponse(eq(beersAPI))

    }

}