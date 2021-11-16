package com.yiqqi.beers.util.dispatcherprovider

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {

    val mainDispatcher: CoroutineDispatcher

    val ioDispatcher: CoroutineDispatcher

    val cpuDispatcher: CoroutineDispatcher

}