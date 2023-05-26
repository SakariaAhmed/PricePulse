package com.example.team33.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocalLaundryService
import androidx.compose.material.icons.rounded.Shower
import androidx.compose.material.icons.rounded.Thermostat
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.team33.R
import com.example.team33.ui.chart.PopulatedChartCard
import com.example.team33.ui.theme.md_theme_light_primaryContainer
import com.example.team33.ui.uistate.MainUiState


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

            Spacer(modifier = Modifier.height(10.dp))

            // Table and graph
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

            Spacer(modifier = Modifier.height(20.dp))

            // Result
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

                // Times the list with the kwh cost of the product
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

            Spacer(modifier = Modifier.height(40.dp))

            // Four buttons
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { changeAppliance("Washing") },
                    modifier = Modifier.size(80.dp),
                    shape = RoundedCornerShape(100),
                    colors = ButtonDefaults.buttonColors(md_theme_light_primaryContainer)

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
                    shape = RoundedCornerShape(100),
                    colors = ButtonDefaults.buttonColors(md_theme_light_primaryContainer)

                ) {
                    Image(
                        painterResource(R.drawable.oven),
                        contentDescription = stringResource(R.string.oven),
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.weight(0.12f))

                Button(
                    onClick = { changeAppliance("Heater") },
                    modifier = Modifier.size(80.dp),
                    shape = RoundedCornerShape(100),
                    colors = ButtonDefaults.buttonColors(md_theme_light_primaryContainer)

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
                    shape = RoundedCornerShape(100),
                    colors = ButtonDefaults.buttonColors(md_theme_light_primaryContainer)

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
    PopulatedChartCard(list = list, modifier, 2.7f)
}

@Composable
fun ShowTable(list: List<Double>, modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.weight(0.25F))
        LazyColumn(modifier = Modifier.height(250.dp)) {
            itemsIndexed(list) { index, element ->
                RowInTable(value = index + 1, price = element)

            }
        }
        Spacer(modifier = Modifier.weight(0.25F))
    }
}

@Composable
fun RowInTable(
    value: Int,
    price: Double,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .border(width = 2.dp, color = Color.Blue)
            .padding(1.dp)
            .height(60.dp)
            .fillMaxWidth(0.8F)
    ) {
        TableCell(data = "$value:")

        //Makes the black line between the 2 cells
        Divider(
            color = Color.Blue,
            thickness = 16.dp,
            modifier = Modifier
                .fillMaxHeight(2F)
                .width(1.dp)
        )
        TableCell(data = "%.2f NOK".format(price))
    }
}

@Composable
fun TableCell(
    data: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(100.dp)
    ) {
        Text(
            text = data,
            modifier = Modifier.padding(8.dp)
        )
    }
}

