package com.yiqqi.beers.util.dispatcherprovider

import kotlinx.coroutines.Dispatchers


class DispatcherProviderImpl: DispatcherProvider {

    override val mainDispatcher = Dispatchers.Main

    override val ioDispatcher = Dispatchers.IO

    override val cpuDispatcher = Dispatchers.Default

}