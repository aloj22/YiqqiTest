package com.yiqqi.beers.ui.list

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import com.yiqqi.beers.LiveDataAndCoroutineDispatcherRule
import com.yiqqi.beers.domain.Beer
import com.yiqqi.beers.usecase.GetBeersUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class BeerListViewModelTest {


    @get:Rule
    val liveDataAndCoroutineDispatcher = LiveDataAndCoroutineDispatcherRule()


    private val beers = listOf<Beer>(mock(), mock(), mock())
    private val getBeersUseCase = mock<GetBeersUseCase>()
    private lateinit var beerDetailViewModel: BeerListViewModel


    @Before
    fun init() = runBlocking {
        val flow = flow {
            emit(beers)
        }
        whenever(getBeersUseCase.getBeers()).thenReturn(flow)
        beerDetailViewModel = BeerListViewModel(getBeersUseCase)
    }


    @Test
    fun `Should set beers values on init`() {
        runBlocking {
            val beersObserver = mock<Observer<List<Beer>>>()
            beerDetailViewModel.beers.observeForever(beersObserver)
            verify(beersObserver).onChanged(eq(beers))
            verify(getBeersUseCase).getBeers()
        }
    }


    @Test
    fun `Should open beer details`() {
        val beer = mock<Beer> {
            on { id } doReturn 2342L
        }
        val goToBeerObserver = mock<Observer<Long>>()
        beerDetailViewModel.goToBeerDetail.observeForever(goToBeerObserver)
        beerDetailViewModel.onBeerClicked(beer)
        verify(goToBeerObserver).onChanged(eq(2342L))
    }

}