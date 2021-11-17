package com.yiqqi.beers.di

import com.yiqqi.beers.data.BeerRepository
import com.yiqqi.beers.data.impl.BeerRepositoryImpl
import com.yiqqi.beers.data.source.remote.RemoteSource
import com.yiqqi.beers.data.source.remote.impl.ApiMapper
import com.yiqqi.beers.data.source.remote.impl.ApiService
import com.yiqqi.beers.data.source.remote.impl.ApiServiceGenerator
import com.yiqqi.beers.data.source.remote.impl.RemoteSourceImpl
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
        remoteSource: RemoteSource
    ): BeerRepository = BeerRepositoryImpl(remoteSource)


    @Provides
    fun provideNetworkSource(
        apiService: ApiService,
        apiMapper: ApiMapper,
        dispatcherProvider: DispatcherProvider
    ): RemoteSource = RemoteSourceImpl(apiService, apiMapper, dispatcherProvider)


    @Provides
    fun provideApiService(): ApiService = ApiServiceGenerator.createService()


    @Provides
    fun provideApiMapper() = ApiMapper()


}