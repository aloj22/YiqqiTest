package com.yiqqi.beers.usecase.impl

import com.yiqqi.beers.data.BeerRepository
import com.yiqqi.beers.usecase.SetBeerAvailableUseCase

class SetBeerAvailableUseCaseImpl(private val beerRepository: BeerRepository) : SetBeerAvailableUseCase {


    override suspend fun setBeerAvailable(beerId: Long, available: Boolean) =
        beerRepository.setBeerAvailability(beerId, available)


}