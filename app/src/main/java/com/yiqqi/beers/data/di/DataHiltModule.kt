package com.yiqqi.beers.data.di

import com.yiqqi.beers.data.BeerRepository
import com.yiqqi.beers.data.impl.BeerRepositoryImpl
import com.yiqqi.beers.data.source.network.NetworkSource
import com.yiqqi.beers.data.source.network.impl.ApiMapper
import com.yiqqi.beers.data.source.network.impl.ApiService
import com.yiqqi.beers.data.source.network.impl.ApiServiceGenerator
import com.yiqqi.beers.data.source.network.impl.NetworkSourceImpl
import com.yiqqi.beers.util.dispatcherprovider.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataHiltModule {


    @Provides
    @Singleton
    fun provideRepository(
        networkSource: NetworkSource
    ): BeerRepository = BeerRepositoryImpl(networkSource)


    @Provides
    fun provideNetworkSource(
        apiService: ApiService,
        apiMapper: ApiMapper,
        dispatcherProvider: DispatcherProvider
    ): NetworkSource = NetworkSourceImpl(apiService, apiMapper, dispatcherProvider)


    @Provides
    fun provideApiService(): ApiService = ApiServiceGenerator.createService()


    @Provides
    fun provideApiMapper() = ApiMapper()


}