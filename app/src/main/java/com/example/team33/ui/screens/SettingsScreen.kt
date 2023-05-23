package com.example.team33.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.*
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.team33.R
import com.example.team33.R.string.purpose
import com.example.team33.network.ElectricityRegion
import com.example.team33.ui.theme.md_theme_dark_background
import com.example.team33.ui.uistates.MainUiState
import com.mikepenz.aboutlibraries.ui.compose.LibrariesContainer

@Composable
fun SettingsScreen(
    mainUiState: MainUiState,
    navController: NavHostController,
    changeElectricityRegion: (ElectricityRegion) -> Unit,
    modifier: Modifier = Modifier
) {
    val openDialog = remember { mutableStateOf(false) }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = stringResource(id = R.string.language))
            Text(text = stringResource(id = R.string.change_language),
                fontSize = 14.sp,fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(500.dp))

            Spacer(modifier = Modifier.height(40.dp))

            Button(onClick = { openDialog.value = !openDialog.value },
                //Select region button
                modifier= Modifier
                    .clip(shape = RoundedCornerShape(size = 75.dp))
                    .height(70.dp)
                    .fillMaxSize()
            ) {

                Text(stringResource(R.string.select_region),
                    fontSize = 25.sp,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(200.dp))
            }

            if (openDialog.value) {

                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(stringResource(R.string.select_region))
                    },
                    text = {
                        val regionOptions: List<String> = listOf(
                            stringResource(id = R.string.east_norway),
                            stringResource(id = R.string.south_norway),
                            stringResource(id = R.string.mid_norway),
                            stringResource(id = R.string.north_norway),
                            stringResource(id = R.string.west_norway)
                        )

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            var selectedOption = remember { mutableStateOf("") }

                            when (mainUiState.currentRegion) {
                                ElectricityRegion.NO1 -> selectedOption.value = regionOptions[0]
                                ElectricityRegion.NO2 -> selectedOption.value = regionOptions[1]
                                ElectricityRegion.NO3 -> selectedOption.value = regionOptions[2]
                                ElectricityRegion.NO4 -> selectedOption.value = regionOptions[3]
                                ElectricityRegion.NO5 -> selectedOption.value = regionOptions[4]
                            }


                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = regionOptions[0])

                                RadioButton(
                                    selected = selectedOption.value == regionOptions[0],
                                    onClick = {
                                        selectedOption.value = regionOptions[0];changeElectricityRegion(
                                        ElectricityRegion.NO1
                                    )
                                    }
                                )

                            }


                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = regionOptions[1])

                                RadioButton(
                                    selected = selectedOption.value == regionOptions[1],
                                    onClick = {
                                        selectedOption.value = regionOptions[1];changeElectricityRegion(
                                        ElectricityRegion.NO2
                                    )
                                    }
                                )

                            }


                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = regionOptions[2])

                                RadioButton(
                                    selected = selectedOption.value == regionOptions[2],
                                    onClick = {
                                        selectedOption.value = regionOptions[2];changeElectricityRegion(
                                        ElectricityRegion.NO3
                                    )
                                    }
                                )

                            }


                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = regionOptions[3])

                                RadioButton(
                                    selected = selectedOption.value == regionOptions[3],
                                    onClick = {
                                        selectedOption.value = regionOptions[3];changeElectricityRegion(
                                        ElectricityRegion.NO4
                                    )
                                    }
                                )

                            }


                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = regionOptions[4])

                                RadioButton(
                                    selected = selectedOption.value == regionOptions[4],
                                    onClick = {
                                        selectedOption.value = regionOptions[4];changeElectricityRegion(
                                        ElectricityRegion.NO5
                                    )
                                    }
                                )

                            }


                        }
                    },

                    confirmButton = {
                        Button(

                            onClick = {
                                openDialog.value = false;


                            }) {
                            Text("Ok")
                        }
                    }
                )


            }
            
            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = { navController.navigate("showAboutThisApp") },
                modifier= Modifier
                    .clip(shape = RoundedCornerShape(size = 75.dp))
                    .height(70.dp)
                    .fillMaxSize()
                //modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(stringResource(id = R.string.about_this_app),
                    fontSize = 25.sp,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(200.dp))
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = { navController.navigate("openSource") },
                modifier= Modifier
                    .clip(shape = RoundedCornerShape(size = 75.dp))
                    .height(70.dp)
                    .fillMaxSize()
                //modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(stringResource(id = R.string.opensource),
                    fontSize = 25.sp,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(300.dp))
            }

            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}


@Composable
fun OpenSource(modifier: Modifier = Modifier) {
    LibrariesContainer(modifier.fillMaxSize())
}

/*@Composable
fun ShowDevelopers(modifier: Modifier = Modifier) {
    val array: Array<String> = stringArrayResource(id = R.array.names)
    Column(modifier = modifier) {
        Text(text = stringResource(id = R.string.made_by))
        array.forEach { selectedOption ->
            Text(text = selectedOption)
        }
    }
}*/



@Composable
fun ShowAboutThisApp(modifier: Modifier = Modifier) {
    val informationText = stringResource(id = R.string.information)
    val paragraphs = informationText.split("\n\n")

    LazyColumn(modifier = modifier) {
        items(paragraphs) { paragraph ->
            Text(
                text = paragraph,
                fontSize = 15.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }
    }
}



@Composable
fun ShowPurpose(modifier: Modifier = Modifier) {
    val purposeText = stringResource(id = R.string.purpose)
    val paragraphs = purposeText.split("\n\n")

    Column(modifier = modifier) {
        paragraphs.forEachIndexed { index, paragraph ->
            Text(
                text = paragraph,
                fontSize = 15.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            if (index != paragraphs.size - 1) {
              //  Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}
