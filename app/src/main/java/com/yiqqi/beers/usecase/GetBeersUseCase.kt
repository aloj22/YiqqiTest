package com.yiqqi.beers.usecase

import com.yiqqi.beers.domain.Beer
import kotlinx.coroutines.flow.Flow

interface GetBeersUseCase {

    suspend fun getBeers(): Flow<List<Beer>>

}