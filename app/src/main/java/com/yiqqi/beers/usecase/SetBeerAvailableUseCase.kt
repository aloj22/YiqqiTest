package com.yiqqi.beers.usecase


interface SetBeerAvailableUseCase {

    suspend fun setBeerAvailable(beerId: Long, available: Boolean)

}