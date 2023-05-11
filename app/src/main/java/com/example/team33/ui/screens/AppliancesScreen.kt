package com.example.team33.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.team33.R
import com.example.team33.ui.uistates.MainUiState
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entryModelOf

@Composable
fun AppliancesScreen(mainUiState: MainUiState, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = stringResource(id = R.string.appliances), fontSize = 40.sp)

        // Lagde en variabel som holder styr på dette for nå
        var showChart by remember { mutableStateOf(true) }
        // Tabell og Graf
        Row {
            Button(
                onClick = { showChart = false },
                modifier = Modifier.clip(shape = RectangleShape)
            ) {
                Text(text = stringResource(id = R.string.table))
            }
            Button(onClick = { showChart = true }) {
                Text(text = stringResource(id = R.string.graph))
            }
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
            //Lagde eksempel data som skal etterligne liste med Kr/kWt
            val liste = arrayListOf(2.1F, 1.5F, 3.2F, 5.3F, 2F, 2.15F, 2.4F)
            if (showChart) {
                ShowGraph(liste)
            } else {
                ShowTable(liste)
            }

        }

        //Fire knapper

        Row {
            Button(onClick = { /*TODO*/ }, Modifier.height(50.dp)) {

            }
            Spacer(modifier = Modifier.padding(10.dp))
            Button(onClick = { /*TODO*/ }, Modifier.height(50.dp)) {

            }
            Spacer(modifier = Modifier.padding(10.dp))

            Button(onClick = { /*TODO*/ }, Modifier.height(50.dp)) {

            }
            Spacer(modifier = Modifier.padding(10.dp))

            Button(onClick = { /*TODO*/ }, Modifier.height(50.dp)) {

            }
        }

        Column(modifier = Modifier.weight(1f)) {
        }
    }
}

@Composable
fun ShowGraph(list: List<Float>) {
    val chartEntryModelProducer =
        entryModelOf(List(list.size) { FloatEntry(it.toFloat(), list[it]) })

    Chart(
        chart = lineChart(),
        model = chartEntryModelProducer,
        startAxis = startAxis(),
        bottomAxis = bottomAxis(),
    )
}

@Composable
fun ShowTable(list: List<Float>) {
    var teller = 0
    LazyColumn {
        items(list) { element ->
            RowInTable(verdi = teller++, pris = element)

        }
    }
}

@Composable
fun RowInTable(verdi: Int, pris: Float) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .border(width = 2.dp, color = Color.Black)
    ) {
        Text(text = "         $verdi :                           ")
        Text(text = "$pris NOK/kWh")
    }
}
