package com.yiqqi.beers.usecase.impl

import com.yiqqi.beers.data.BeerRepository
import com.yiqqi.beers.usecase.GetBeersUseCase

class GetBeersUseCaseImpl(private val beerRepository: BeerRepository) : GetBeersUseCase {

    override fun getBeers() = beerRepository.getBeers()

}