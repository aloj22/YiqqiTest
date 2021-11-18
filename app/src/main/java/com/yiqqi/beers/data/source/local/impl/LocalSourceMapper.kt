package com.yiqqi.beers.data.source.local.impl

import com.yiqqi.beers.data.source.local.impl.model.BeerItemAvailability
import com.yiqqi.beers.data.source.local.impl.model.BeerItemDB
import com.yiqqi.beers.domain.Beer

class LocalSourceMapper {


    fun beerFromBeerItemDB(beerItemAvailability: BeerItemAvailability) = Beer(
        beerItemAvailability.beer.id,
        beerItemAvailability.beer.name,
        beerItemAvailability.beer.description,
        beerItemAvailability.beer.tagline,
        beerItemAvailability.beer.image,
        beerItemAvailability.beer.abv,
        beerItemAvailability.beer.ibu,
        beerItemAvailability.beer.foodPairing,
        beerItemAvailability.availability?.available ?: true,
    )

    fun beersFromBeerItemDBList(beerItemsAvailability: List<BeerItemAvailability>) = beerItemsAvailability.map(::beerFromBeerItemDB)

    fun beerItemDBFromBeer(beer: Beer) = BeerItemDB(
        beer.id,
        beer.name,
        beer.description,
        beer.tagline,
        beer.image,
        beer.abv,
        beer.ibu,
        beer.foodPairing,
    )

    fun beersItemDBFromBeerList(beers: List<Beer>) = beers.map(::beerItemDBFromBeer)


}