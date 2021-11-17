package com.yiqqi.beers.ui.detail

import androidx.lifecycle.*
import com.yiqqi.beers.domain.Beer
import com.yiqqi.beers.usecase.GetBeerUseCase
import com.yiqqi.beers.usecase.SetBeerAvailableUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named



@HiltViewModel
class BeerDetailViewModel @Inject constructor(
    @Named("beerId") private val beerId: Long,
    private val getBeerUseCase: GetBeerUseCase,
    private val setBeerAvailableUseCase: SetBeerAvailableUseCase,
) : ViewModel() {



    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> get() = _description

    private val _image = MutableLiveData<String>()
    val image: LiveData<String> get() = _image

    private val _tagline = MutableLiveData<String>()
    val tagline: LiveData<String> get() = _tagline

    private val _abv = MutableLiveData<Float>()
    val abv: LiveData<Float> get() = _abv

    private val _ibu = MutableLiveData<String>()
    val ibu: LiveData<String> get() = _ibu

    private val _foodPairing = MutableLiveData<List<String>>()
    val foodPairing: LiveData<List<String>> get() = _foodPairing

    private val _showAvailable = MutableLiveData<ShowAvailableInfo>()
    val showAvailable: LiveData<ShowAvailableInfo> get() = _showAvailable


    private var currentBeer: Beer? = null
    private var firstLoad = true


    init {
        viewModelScope.launch {
            getBeerUseCase.getBeer(beerId).filterNotNull().collect(::updateBeer)
        }
    }


    fun toggleBeerAvailability() {
        currentBeer?.let {
            viewModelScope.launch {
                setBeerAvailableUseCase.setBeerAvailable(it.id, !it.available)
            }
        }
    }


    private fun updateBeer(beer: Beer) {
        currentBeer = beer
        _name.value = beer.name
        _description.value = beer.description
        _image.value = beer.image
        _tagline.value = beer.tagline
        _abv.value =  beer.abv
        _ibu.value = beer.ibu.toString()
        _foodPairing.value = beer.foodPairing
        _showAvailable.value = ShowAvailableInfo(beer.available, !firstLoad)
        firstLoad = false
    }

}

data class ShowAvailableInfo(val available: Boolean, val animate: Boolean)