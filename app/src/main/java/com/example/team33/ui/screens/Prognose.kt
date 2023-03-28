package com.example.team33.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entryModelOf

@Composable
fun PrognoseScreen() {
    // Toppbar
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Prognose", fontSize = 40.sp)
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            //Lagde en variabel som holder styr på dette for nå
            var showChart by remember { mutableStateOf(true) }
            // Tabell og Graf
            Row() {
                Button(onClick = { showChart=false }, modifier = Modifier.clip(shape = RectangleShape)) {
                    Text(text = "Tabell")
                }
                Button(onClick = { showChart=true }) {
                    Text(text = "Graf")
                }
            }
            //Resultat
            Box() {
                Box(
                    Modifier
                        .align(Center)
                        .height(200.dp)
                        .width(
                            300.dp
                        )

                )
                //Lagde eksempel data som skal etterligne liste med Kr/kWt
                val liste = arrayListOf(2.1F,1.5F,3.2F,5.3F,2F, 2.15F,2.4F)
                if (showChart){
                    ShowGraph(liste)
                }else{
                    ShowTable(liste)
                }

            }

            //Fire knapper

            Row() {
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


        }
    }


}
@Composable
fun ShowGraph(list: List<Float>){
    val chartEntryModelProducer = entryModelOf(List(list.size){ FloatEntry(it.toFloat(),list[it] ) })

    Chart(
        chart = lineChart(),
        model = chartEntryModelProducer,
        startAxis = startAxis(),
        bottomAxis = bottomAxis(),
    )
}

@Composable
fun ShowTable(list:List<Float>){
    var teller=0
    LazyColumn(
    ){
        items(list) {element ->
            RowInTable(verdi = teller++ , pris = element)

        }
    }
}

@Composable
fun RowInTable(verdi:Int,pris:Float){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .border(width = 2.dp, color = Color.Black)) {
        Text(text="         $verdi :                           ")
        Text(text="$pris kr/kWt")
    }
}