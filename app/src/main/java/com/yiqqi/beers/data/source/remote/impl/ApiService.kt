package com.yiqqi.beers.data.source.remote.impl

import com.yiqqi.beers.data.source.remote.model.BeerResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("v2/beers/{beer_id}")
    suspend fun getBeer(@Path("beer_id") beerId: Long): List<BeerResponse>


    @GET("v2/beers")
    suspend fun getBeers(@Query("page") page: Int, @Query("per_page") count: Int): List<BeerResponse>

}