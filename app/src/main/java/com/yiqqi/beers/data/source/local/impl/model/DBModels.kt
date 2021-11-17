package com.yiqqi.beers.data.source.local.impl.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation


@Entity(tableName = BeerItemDB.TABLE_NAME)
data class BeerItemDB(
    @PrimaryKey val id: Long,
    val name: String,
    val description: String,
    val tagline: String,
    val image: String,
    val abv: Float,
    val ibu: Float,
    val foodPairing: List<String>,
) {

    companion object {
        const val TABLE_NAME = "BeerItemDB"
    }

}

@Entity(tableName = BeerAvailabilityDB.TABLE_NAME)
data class BeerAvailabilityDB(
    @PrimaryKey val beerId: Long,
    val available: Boolean
) {

    companion object {
        const val TABLE_NAME = "BeerAvailabilityDB"
    }

}


data class BeerItemAvailability(
    @Embedded val beer: BeerItemDB,
    @Relation(parentColumn = "id", entityColumn = "beerId") val availability: BeerAvailabilityDB?
)