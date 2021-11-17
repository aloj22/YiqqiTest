package com.yiqqi.beers.di

import android.content.Context
import com.yiqqi.beers.data.BeerRepository
import com.yiqqi.beers.data.impl.BeerRepositoryImpl
import com.yiqqi.beers.data.source.local.LocalSource
import com.yiqqi.beers.data.source.local.impl.LocalSourceImpl
import com.yiqqi.beers.data.source.local.impl.LocalSourceMapper
import com.yiqqi.beers.data.source.local.impl.database.BeerDAO
import com.yiqqi.beers.data.source.local.impl.database.BeerDatabase
import com.yiqqi.beers.data.source.remote.RemoteSource
import com.yiqqi.beers.data.source.remote.impl.ApiMapper
import com.yiqqi.beers.data.source.remote.impl.ApiService
import com.yiqqi.beers.data.source.remote.impl.ApiServiceGenerator
import com.yiqqi.beers.data.source.remote.impl.RemoteSourceImpl
import com.yiqqi.beers.util.dispatcherprovider.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataHiltModule {


    @Provides
    @Singleton
    fun provideRepository(
        remoteSource: RemoteSource,
        localSource: LocalSource
    ): BeerRepository = BeerRepositoryImpl(remoteSource, localSource)


    @Provides
    fun provideRemoteSource(
        apiService: ApiService,
        apiMapper: ApiMapper,
        dispatcherProvider: DispatcherProvider
    ): RemoteSource = RemoteSourceImpl(apiService, apiMapper, dispatcherProvider)


    @Provides
    fun provideApiService(): ApiService = ApiServiceGenerator.createService()


    @Provides
    fun provideApiMapper() = ApiMapper()


    @Provides
    @Singleton
    fun provideBeerDatabase(
        @ApplicationContext context: Context
    ): BeerDatabase = BeerDatabase.getInstance(context)


    @Provides
    @Singleton
    fun provideBeerDAO(
        database: BeerDatabase
    ): BeerDAO = database.beerDAO()


    @Provides
    fun provideLocalSource(
        beerDAO: BeerDAO,
        localSourceMapper: LocalSourceMapper,
        dispatcherProvider: DispatcherProvider
    ): LocalSource = LocalSourceImpl(beerDAO, localSourceMapper, dispatcherProvider)


    @Provides
    fun provideLocalSourceMapper() = LocalSourceMapper()

}