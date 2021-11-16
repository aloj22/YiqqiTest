package com.yiqqi.beers.di

import com.yiqqi.beers.data.BeerRepository
import com.yiqqi.beers.usecase.GetBeerUseCase
import com.yiqqi.beers.usecase.GetBeersUseCase
import com.yiqqi.beers.usecase.impl.GetBeerUseCaseImpl
import com.yiqqi.beers.usecase.impl.GetBeersUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseHiltModule {


    @Provides
    fun provideGetBeerUseCase(
        beerRepository: BeerRepository
    ): GetBeerUseCase = GetBeerUseCaseImpl(beerRepository)

    @Provides
    fun provideGetBeersUseCase(
        beerRepository: BeerRepository
    ): GetBeersUseCase = GetBeersUseCaseImpl(beerRepository)


}