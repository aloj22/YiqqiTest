package com.yiqqi.beers.di

import com.yiqqi.beers.util.dispatcherprovider.DispatcherProvider
import com.yiqqi.beers.util.dispatcherprovider.DispatcherProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UtilHiltModule {

    @Provides
    fun bindDispatcherProvider(): DispatcherProvider = DispatcherProviderImpl()

}