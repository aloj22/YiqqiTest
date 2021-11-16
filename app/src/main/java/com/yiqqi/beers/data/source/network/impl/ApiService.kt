package com.yiqqi.beers.data.source.network.impl

import com.yiqqi.beers.data.source.network.model.BeerResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {


    @GET("v2/beers")
    suspend fun getBeers(@Query("page") page: Int, @Query("per_page") count: Int): List<BeerResponse>

}