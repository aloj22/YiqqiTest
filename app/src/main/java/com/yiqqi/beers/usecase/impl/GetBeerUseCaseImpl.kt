package com.yiqqi.beers.usecase.impl

import com.yiqqi.beers.data.BeerRepository
import com.yiqqi.beers.usecase.GetBeerUseCase
import com.yiqqi.beers.usecase.GetBeersUseCase

class GetBeerUseCaseImpl(private val beerRepository: BeerRepository) : GetBeerUseCase {

    override suspend fun getBeer(beerId: Long) = beerRepository.getBeer(beerId)

}