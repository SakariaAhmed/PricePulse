package com.example.team33.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
            // Tabell og Graf
            Row() {
                Button(onClick = { /*TODO*/ }, modifier = Modifier.clip(shape = RectangleShape)) {
                    Text(text = "Tabell")
                }
                Button(onClick = { /*TODO*/ }) {
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
                        .background(Color.Red)
                )
                Text(text = "Resultat", fontSize = 20.sp)
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