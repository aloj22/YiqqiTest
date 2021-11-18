package com.yiqqi.beers.data.impl

import com.nhaarman.mockitokotlin2.*
import com.yiqqi.beers.LiveDataAndCoroutineDispatcherRule
import com.yiqqi.beers.data.source.local.LocalSource
import com.yiqqi.beers.data.source.remote.RemoteSource
import com.yiqqi.beers.domain.Beer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BeerRepositoryImplTest {



    @get:Rule
    val liveDataAndCoroutineDispatcher = LiveDataAndCoroutineDispatcherRule()

    private val localSource = mock<LocalSource>()
    private val remoteSource = mock<RemoteSource>()
    private val beerRepositoryImpl = BeerRepositoryImpl(remoteSource, localSource)


    @Test
    fun `Should get and update beer`() = runBlockingTest {

        val beerId = 23423L
        val beer1 = mock<Beer>()
        val beer2 = mock<Beer>()
        val flow = flow {
            emit(beer1)
        }
        whenever(localSource.getBeer(any())).thenReturn(flow)
        whenever(remoteSource.getBeer(any())).thenReturn(beer2)

        val flowResponse = beerRepositoryImpl.getBeer(beerId)
        flowResponse.collect {
            assertEquals(beer1, it)
        }

        verify(localSource).getBeer(eq(beerId))
        verify(remoteSource).getBeer(eq(beerId))
        verify(localSource).updateBeer(eq(beer2))

    }

    @Test
    fun `Should get and update beers`() = runBlockingTest {

        val beers1 = listOf<Beer>(mock())
        val beers2 = listOf<Beer>(mock())
        val beers3 = listOf<Beer>(mock())
        val flow = flow {
            emit(beers1)
        }
        whenever(localSource.getBeers()).thenReturn(flow)
        whenever(remoteSource.getBeers(any(), any())).thenReturn(beers2, beers3)

        val flowResponse = beerRepositoryImpl.getBeers()
        flowResponse.collect {
            assertEquals(beers1, it)
        }

        verify(localSource).getBeers()
        verify(remoteSource).getBeers(eq(1), eq(50))
        verify(remoteSource).getBeers(eq(2), eq(50))

        val argumentCaptor = argumentCaptor<List<Beer>>()
        verify(localSource, times(2)).updateBeers(argumentCaptor.capture())

        assertEquals(beers2, argumentCaptor.allValues[argumentCaptor.allValues.size - 2])
        assertEquals(beers3, argumentCaptor.allValues[argumentCaptor.allValues.size - 1])

    }

    @Test
    fun `Should set beer availability`() = runBlockingTest {
        val beerId = 4653453L
        beerRepositoryImpl.setBeerAvailability(beerId, true)
        verify(localSource).setBeerAvailability(eq(beerId), eq(true))

        beerRepositoryImpl.setBeerAvailability(beerId, false)
        verify(localSource).setBeerAvailability(eq(beerId), eq(false))
    }

}