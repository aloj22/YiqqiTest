package com.yiqqi.beers.domain

data class Beer(
    val id: Long,
    val name: String,
    val description: String,
    val tagline: String,
    val image: String,
    val abv: Float,
    val ibu: Float,
    val foodPairing: List<String>,
    val available: Boolean
)
