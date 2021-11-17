package com.yiqqi.beers.ui.list

import androidx.lifecycle.*
import com.yiqqi.beers.domain.Beer
import com.yiqqi.beers.usecase.GetBeersUseCase
import com.yiqqi.beers.util.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BeerListViewModel @Inject constructor(
    private val getBeersUseCase: GetBeersUseCase
) : ViewModel() {


    /*companion object {

        private const val MAX_ITEMS_TO_SHOW = 100
        private const val PAGE_SIZE = 50

    }*/


    private val _beers = MutableLiveData<List<Beer>>()
    val beers: LiveData<List<Beer>> get() = _beers

    private val _goToBeerDetail = LiveEvent<Long>()
    val goToBeerDetail: LiveData<Long> get() = _goToBeerDetail

    //private var currentPage = 1

    init {
        viewModelScope.launch {
            getBeersUseCase.getBeers().collect {
                _beers.value = it
            }
        }
    }


    fun onBeerClicked(beer: Beer) {
        _goToBeerDetail.value = beer.id
    }


}