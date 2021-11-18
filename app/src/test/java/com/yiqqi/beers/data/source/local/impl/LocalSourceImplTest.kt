package com.yiqqi.beers.data.source.local.impl

import com.nhaarman.mockitokotlin2.*
import com.yiqqi.beers.DispatcherProviderTestImpl
import com.yiqqi.beers.LiveDataAndCoroutineDispatcherRule
import com.yiqqi.beers.data.source.local.impl.database.BeerDAO
import com.yiqqi.beers.data.source.local.impl.model.BeerAvailabilityDB
import com.yiqqi.beers.data.source.local.impl.model.BeerItemAvailability
import com.yiqqi.beers.data.source.local.impl.model.BeerItemDB
import com.yiqqi.beers.domain.Beer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LocalSourceImplTest {


    private val beerDAO = mock<BeerDAO>()
    private val localSourceMapper = mock<LocalSourceMapper>()


    private val localSourceImpl = LocalSourceImpl(beerDAO, localSourceMapper, DispatcherProviderTestImpl())

    @get:Rule
    val liveDataAndCoroutineDispatcher = LiveDataAndCoroutineDispatcherRule()

    @Test
    fun `Should get beers`() = runBlockingTest {

        val mockDBList = listOf<BeerItemAvailability>(mock(), mock(), mock())
        val mockDBFlow = flow {
            emit(mockDBList)
        }

        val mockMappedList = listOf<Beer>(mock(), mock(), mock())

        beerDAO.stub {
            on { getBeers() } doReturn mockDBFlow
        }
        localSourceMapper.stub {
            on { beersFromBeerItemDBList(any()) } doReturn mockMappedList
        }

        val response = localSourceImpl.getBeers()
        response.collect {
            assertEquals(mockMappedList, it)
        }

        verify(beerDAO).getBeers()
        verify(localSourceMapper).beersFromBeerItemDBList(eq(mockDBList))

    }


    @Test
    fun `Should get beer`() = runBlockingTest {

        val beerId = 654L

        val mockDB = mock<BeerItemAvailability>()
        val mockDBFlow = flow {
            emit(mockDB)
        }

        val mockBeer = mock<Beer>()

        beerDAO.stub {
            on { getBeer(any()) } doReturn mockDBFlow
        }
        localSourceMapper.stub {
            on { beerFromBeerItemDB(any()) } doReturn mockBeer
        }

        val response = localSourceImpl.getBeer(beerId)
        response.collect {
            assertEquals(mockBeer, it)
        }

        verify(beerDAO).getBeer(eq(beerId))
        verify(localSourceMapper).beerFromBeerItemDB(eq(mockDB))

    }


    @Test
    fun `Should update beer`() = runBlockingTest {

        val beer = mock<Beer>()
        val beerDB = mock<BeerItemDB>()

        localSourceMapper.stub {
            on { beerItemDBFromBeer(any()) } doReturn beerDB
        }

        localSourceImpl.updateBeer(beer)

        verify(beerDAO).insertBeer(eq(beerDB))
        verify(localSourceMapper).beerItemDBFromBeer(eq(beer))

    }

    @Test
    fun `Should update beers`() = runBlockingTest {

        val beers = listOf<Beer>(mock(), mock(), mock())
        val beerDB = listOf<BeerItemDB>(mock(), mock(), mock())

        localSourceMapper.stub {
            on { beersItemDBFromBeerList(any()) } doReturn beerDB
        }

        localSourceImpl.updateBeers(beers)

        verify(beerDAO).insertBeers(eq(beerDB))
        verify(localSourceMapper).beersItemDBFromBeerList(eq(beers))

    }

    @Test
    fun `Should set availability`() = runBlockingTest {

        val beerId = 531L

        localSourceImpl.setBeerAvailability(beerId, true)

        val argumentCaptor = argumentCaptor<BeerAvailabilityDB>()
        verify(beerDAO).updateBeerAvailability(argumentCaptor.capture())

        assertEquals(beerId, argumentCaptor.lastValue.beerId)
        assertEquals(true, argumentCaptor.lastValue.available)


        localSourceImpl.setBeerAvailability(beerId, false)

        verify(beerDAO, times(2)).updateBeerAvailability(argumentCaptor.capture())

        assertEquals(beerId, argumentCaptor.lastValue.beerId)
        assertEquals(false, argumentCaptor.lastValue.available)

    }



}