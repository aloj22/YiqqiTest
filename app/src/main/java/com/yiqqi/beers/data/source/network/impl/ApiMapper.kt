package com.yiqqi.beers.data.source.network.impl

import com.yiqqi.beers.data.source.network.model.BeerResponse
import com.yiqqi.beers.domain.Beer

class ApiMapper {

    fun beerFromBeerResponse(beerResponse: BeerResponse) = Beer(
        beerResponse.id,
        beerResponse.name,
        beerResponse.description,
        beerResponse.tagline,
        beerResponse.image,
        beerResponse.abv,
        beerResponse.ibu,
        beerResponse.foodPairing,
        true //Review this
    )

}