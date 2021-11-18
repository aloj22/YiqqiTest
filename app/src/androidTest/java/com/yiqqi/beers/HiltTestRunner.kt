package com.yiqqi.beers

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication
import com.github.tmurakami.dexopener.DexOpener

@Suppress("unused")
class HiltTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        DexOpener.install(this)
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }

}