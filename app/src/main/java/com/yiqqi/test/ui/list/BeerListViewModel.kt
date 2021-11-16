package com.yiqqi.test.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yiqqi.test.domain.Beer
import com.yiqqi.test.util.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BeerListViewModel @Inject constructor() : ViewModel() {



    private val _beers = MutableLiveData<List<Beer>>()
    val beers: LiveData<List<Beer>> get() = _beers

    private val _goToBeerDetail = LiveEvent<Long>()
    val goToBeerDetail: LiveData<Long> get() = _goToBeerDetail


    init {
        val l = mutableListOf<Beer>()
        repeat(50) {
            l.add(Beer(
                0,
                "1",
                "asdfsdfasdf",
                "taglineeee",
                "https://images.punkapi.com/v2/192.png",
                3f,
                6f,
                emptyList(),
                true
            ))
        }
        _beers.value = l
    }


    fun onBeerClicked(beer: Beer) {
        _goToBeerDetail.value = beer.id
    }


}