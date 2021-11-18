package com.yiqqi.beers.ui.detail

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import com.yiqqi.beers.LiveDataAndCoroutineDispatcherRule
import com.yiqqi.beers.domain.Beer
import com.yiqqi.beers.usecase.GetBeerUseCase
import com.yiqqi.beers.usecase.SetBeerAvailableUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class BeerDetailViewModelTest {

    @get:Rule
    val liveDataAndCoroutineDispatcher = LiveDataAndCoroutineDispatcherRule()

    private val beerId = 345234L
    private var available = true
    private val beer = Beer(
        beerId,
        "name",
        "description",
        "tagline",
        "image",
        0.79f,
        0.26f,
        listOf("Meat", "Fish", "Rice"),
        available
    )
    private val getBeerUseCase = mock<GetBeerUseCase>()
    private val setBeerAvailableUseCase = mock<SetBeerAvailableUseCase>()
    private lateinit var beerDetailViewModel: BeerDetailViewModel


    @Before
    fun init() = runBlocking {
        val flow = flow {
            emit(beer)
        }
        whenever(getBeerUseCase.getBeer(any())).thenReturn(flow)
        beerDetailViewModel = BeerDetailViewModel(beerId, getBeerUseCase, setBeerAvailableUseCase)
    }


    @Test
    fun `Should set beer values on init`() = runBlocking {

        val name = mock<Observer<String>>()
        val description = mock<Observer<String>>()
        val image = mock<Observer<String>>()
        val tagline = mock<Observer<String>>()
        val abv = mock<Observer<Float>>()
        val ibu = mock<Observer<String>>()
        val foodPairing = mock<Observer<List<String>>>()
        val showAvailable = mock<Observer<ShowAvailableInfo>>()
        beerDetailViewModel.name.observeForever(name)
        beerDetailViewModel.description.observeForever(description)
        beerDetailViewModel.image.observeForever(image)
        beerDetailViewModel.tagline.observeForever(tagline)
        beerDetailViewModel.abv.observeForever(abv)
        beerDetailViewModel.ibu.observeForever(ibu)
        beerDetailViewModel.foodPairing.observeForever(foodPairing)
        beerDetailViewModel.showAvailable.observeForever(showAvailable)


        verify(getBeerUseCase).getBeer(eq(beerId))
        verify(name).onChanged(eq("name"))
        verify(description).onChanged(eq("description"))
        verify(image).onChanged(eq("image"))
        verify(name).onChanged(eq("name"))
        verify(tagline).onChanged(eq("tagline"))
        verify(abv).onChanged(eq(0.79f))
        verify(ibu).onChanged(eq("0.26"))
        verify(showAvailable).onChanged(argThat {
            available && !animate
        })
        verify(foodPairing).onChanged(argThat {
            this == listOf("Meat", "Fish", "Rice")
        })

    }

    @Test
    fun `Should set beer availability`() = runBlocking {
        beerDetailViewModel.toggleBeerAvailability()
        verify(setBeerAvailableUseCase).setBeerAvailable(eq(beerId), eq(false))
    }


}