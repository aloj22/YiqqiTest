package com.yiqqi.beers.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext


/**
 * Launches a new coroutine to be executed asynchronously
 */
suspend fun launchAsync(coroutine: suspend () -> Unit) {
    with(CoroutineScope(coroutineContext)) {
        launch {
            coroutine()
        }
    }
}