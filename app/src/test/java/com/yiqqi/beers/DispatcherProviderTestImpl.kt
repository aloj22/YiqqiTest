package com.yiqqi.beers

import com.yiqqi.beers.util.dispatcherprovider.DispatcherProvider
import kotlinx.coroutines.Dispatchers


class DispatcherProviderTestImpl: DispatcherProvider {

    override val mainDispatcher = Dispatchers.Main

    override val ioDispatcher = Dispatchers.Main

    override val cpuDispatcher = Dispatchers.Main

}