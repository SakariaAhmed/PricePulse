package com.example.team33.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.team33.R
import com.example.team33.ui.uistates.MainUiState
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import com.example.team33.network.ElectricityRegion
import com.example.team33.ui.chart.PopulatedChartCard


@Composable
fun AppliancesScreen(
    mainUiState: MainUiState, modifier: Modifier = Modifier,
    graphVisible: (Boolean) -> Unit,
    changeAppliance: (String) -> Unit
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.fillMaxSize(),
    ) {
        item {
            // Tabell og Graf
            Row(modifier.fillMaxWidth()) {
                Spacer(Modifier.weight(0.5f))
                Button(
                    onClick = { graphVisible(true) },
                    modifier = Modifier
                        .clip(shape = RectangleShape)
                        .weight(4f)
                ) {
                    Text(
                        text = stringResource(id = R.string.graph),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
                Spacer(Modifier.weight(0.5f))
                Button(
                    onClick = { graphVisible(false) },
                    modifier = Modifier
                        .clip(shape = RectangleShape)
                        .weight(4f)
                ) {
                    Text(
                        text = stringResource(id = R.string.table),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
                Spacer(Modifier.weight(0.5f))


            }
            //Resultat
            Box {
                Box(
                    Modifier
                        .align(Center)
                        .height(200.dp)
                        .width(
                            300.dp
                        )

                )

                var liste = mainUiState.electricityPrices
                // times the list with the kwh cost of the product
                if (liste != null) {
                    when (mainUiState.appliance) {
                        "Washing" -> liste = liste.map { it * 0.57 }
                        "Oven" -> liste = liste.map { it * 1.9 }
                        "Heater" -> liste = liste.map { it * 0.9 }
                        "Shower" -> liste = liste.map { it * 6 }

                    }
                }

                if (liste != null) {
                    if (mainUiState.showGraph) {
                        ShowGraph(liste)
                    } else {
                        Box(modifier = Modifier.fillMaxHeight(0.5F)) {
                            ShowTable(liste)
                        }
                    }
                }
            }

            //Fire knapper
            val farge = ButtonDefaults.buttonColors(White)
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { changeAppliance("Washing") },
                    modifier = Modifier.size(80.dp),
                    shape = RoundedCornerShape(100)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.LocalLaundryService,
                        contentDescription = stringResource(R.string.washing_machine),
                        tint = Color.Black,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.weight(0.12f))
                Button(
                    onClick = { changeAppliance("Oven") },
                    modifier = Modifier.size(80.dp),
                    shape = RoundedCornerShape(100)
                ) {
                    Image(
                        painterResource(R.drawable.oven),
                        contentDescription = stringResource(R.string.oven),
                        modifier = Modifier.fillMaxSize()
                    )
                    /*
                        Icon(
                            imageVector = painterResource(R.drawable.oven_gen_48px) ,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )*/
                }
                Spacer(modifier = Modifier.weight(0.12f))

                Button(
                    onClick = { changeAppliance("Heater") },
                    modifier = Modifier.size(80.dp),
                    shape = RoundedCornerShape(100)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Thermostat,
                        contentDescription = stringResource(R.string.heater),
                        tint = Color.Black,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.weight(0.12f))

                Button(
                    onClick = { changeAppliance("Shower") },
                    modifier = Modifier.size(80.dp),
                    shape = RoundedCornerShape(100)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Shower,
                        contentDescription = stringResource(R.string.shower),
                        tint = Color.Black,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun ShowGraph(list: List<Double>, modifier: Modifier = Modifier) {
    PopulatedChartCard(list = list, Modifier, 2.7f)
}

@Composable
fun ShowTable(list: List<Double>) {
    Row(Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.weight(0.25F))
        LazyColumn() {
            itemsIndexed(list) { index, element ->
                RowInTable(verdi = index + 1, pris = element)

            }
        }
        Spacer(modifier = Modifier.weight(0.25F))
    }
}

@Composable
fun RowInTable(verdi: Int, pris: Double) {
    Row(
        modifier = Modifier
            .border(width = 2.dp, color = Color.Blue)
            .padding(1.dp)
            .height(60.dp)
    ) {
        TableCell(data = "$verdi:")
        //Makes the black line betweem the 2 cells
        Divider(
            color = Color.Blue,
            thickness = 16.dp,
            modifier = Modifier
                .fillMaxHeight(2F)
                .width(1.dp)
        )
        TableCell(data = "$pris NOK")
    }
}

@Composable
fun TableCell(data: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.28F)
    ) {
        Text(
            text = data,
            modifier = Modifier.padding(8.dp)
        )
    }
}

