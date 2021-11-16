package com.yiqqi.beers.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yiqqi.beers.data.BeerRepository
import com.yiqqi.beers.domain.Beer
import com.yiqqi.beers.util.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerListViewModel @Inject constructor(
    private val repository: BeerRepository
) : ViewModel() {



    private val _beers = MutableLiveData<List<Beer>>()
    val beers: LiveData<List<Beer>> get() = _beers

    private val _goToBeerDetail = LiveEvent<Long>()
    val goToBeerDetail: LiveData<Long> get() = _goToBeerDetail


    init {
        /*val l = mutableListOf<Beer>()
        repeat(50) {
            l.add(Beer(
                0,
                "Punk IPA 2007 - 2010",
                "asdfsdfasdf",
                "Post Modern Classic. Spiky. Tropical. Hoppy.",
                "https://images.punkapi.com/v2/192.png",
                3f,
                6f,
                emptyList(),
                true
            ))
        }
        _beers.value = l*/


        viewModelScope.launch {
            repository.getBeers().collect {
                _beers.value = it
            }
        }


    }


    fun onBeerClicked(beer: Beer) {
        _goToBeerDetail.value = beer.id
    }


}