package com.yiqqi.beers.ui.detail

import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.yiqqi.beers.launchFragmentInHiltContainer
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


//TODO not working because hilt doesnt support inserting mock ViewModels
@HiltAndroidTest
class BeerDetailFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)



    private val beerId = 32154L
    private val nameLiveData = MutableLiveData<String>()
    @BindValue var viewModel = mock<BeerDetailViewModel> {
        on { name } doReturn nameLiveData
    }


    @Before
    fun setUp() {
        launchFragmentInHiltContainer<BeerDetailFragment>(BeerDetailFragmentArgs(beerId).toBundle())
        hiltRule.inject()
    }

    @Test
    fun shouldObserveViewModel() {
        launchFragmentInHiltContainer<BeerDetailFragment>(BeerDetailFragmentArgs(beerId).toBundle())
        Assert.assertTrue(nameLiveData.hasActiveObservers())
    }

}