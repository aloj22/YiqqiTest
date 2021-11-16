package com.yiqqi.beers.ui.detail

import androidx.lifecycle.SavedStateHandle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import java.security.InvalidParameterException
import javax.inject.Named


@Module
@InstallIn(ViewModelComponent::class)
object BeerDetailModule {


    @Provides
    @Named("beerId")
    fun beerIdProvider(savedStateHandle: SavedStateHandle): Long =
        savedStateHandle.get<Long>("beer_id") ?: throw InvalidParameterException("Param beer_id not provided")

}