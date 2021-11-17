package com.yiqqi.beers.usecase

import com.yiqqi.beers.domain.Beer
import kotlinx.coroutines.flow.Flow

interface GetBeerUseCase {

    suspend fun getBeer(beerId: Long): Flow<Beer?>

}