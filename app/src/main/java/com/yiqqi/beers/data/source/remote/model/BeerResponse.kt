package com.yiqqi.beers.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class BeerResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("tagline") val tagline: String,
    @SerializedName("image_url") val image: String,
    @SerializedName("abv") val abv: Float,
    @SerializedName("ibu") val ibu: Float,
    @SerializedName("food_pairing") val foodPairing: List<String>,
)