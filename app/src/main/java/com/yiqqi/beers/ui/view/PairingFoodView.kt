package com.yiqqi.beers.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.yiqqi.beers.R




@Composable
fun PairingFood(pairingFood: LiveData<List<String>>) {
    val pairingFoodItems by pairingFood.observeAsState()
    PairingFoodView(
        pairingFoodItems = pairingFoodItems ?: emptyList()
    )
}

@Composable
fun PairingFoodView(pairingFoodItems: List<String>) {
    if (pairingFoodItems.isNotEmpty()) {
        Column {

            Text(
                text = stringResource(id = R.string.beer_detail_pairing_food),
                fontSize = dimensionResource(id = R.dimen.text_size_normal).value.sp,
                color = Color.DarkGray,
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_small)))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.margin_default)),
            ) {
                items(pairingFoodItems) { item ->
                    PairingItem(item)
                }
            }
        }
    }
}

@Composable
private fun PairingItem(item: String) {
    Column(
        Modifier
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(16.dp),
            )
            .background(
                shape = RoundedCornerShape(16.dp),
                color = Color.LightGray,
            )
            .padding(
                horizontal = dimensionResource(id = R.dimen.margin_default),
                vertical = dimensionResource(id = R.dimen.margin_small)
            ),
    )
    {
        Text(
            text = item,
            color = Color.DarkGray,
        )
        Spacer(modifier = Modifier.width(20.dp))
    }
}


@Preview
@Composable
fun PairingFoodViewPreview() {
    PairingFoodView(pairingFoodItems = listOf("Meat", "Fish", "Rice", "Pasta"))
}