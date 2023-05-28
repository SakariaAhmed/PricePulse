package com.example.team33.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.team33.R
import com.example.team33.network.ElectricityRegion
import com.example.team33.ui.uistate.MainUiState
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
        modifier = modifier.fillMaxSize()
    ) {
        item {
            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = stringResource(id = R.string.language))

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(id = R.string.change_language),
                fontSize = 14.sp, fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(500.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = { openDialog.value = !openDialog.value },

                //Select region button
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(size = 75.dp))
                    .height(70.dp)
                    .fillMaxSize()
            ) {

                Text(
                    stringResource(R.string.select_region),
                    fontSize = 25.sp,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(200.dp)
                )
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
                            val selectedOption = remember { mutableStateOf("") }

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
                                        selectedOption.value =
                                            regionOptions[0];changeElectricityRegion(
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
                                        selectedOption.value =
                                            regionOptions[1];changeElectricityRegion(
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
                                        selectedOption.value =
                                            regionOptions[2];changeElectricityRegion(
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
                                        selectedOption.value =
                                            regionOptions[3];changeElectricityRegion(
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
                                        selectedOption.value =
                                            regionOptions[4];changeElectricityRegion(
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
                                openDialog.value = false
                            }) {
                            Text("Ok")
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            //Button which navigates you to the showAboutThisApp composable
            Button(
                onClick = { navController.navigate("showAboutThisApp") },
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(size = 75.dp))
                    .height(70.dp)
                    .fillMaxSize()
            ) {
                Text(
                    stringResource(id = R.string.about_this_app),
                    fontSize = 25.sp,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(200.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            //Button which navigates you to the showPurpose composable
            Button(
                onClick = { navController.navigate("showPurpose") },
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(size = 75.dp))
                    .height(70.dp)
                    .fillMaxSize()
            ) {
                Text(
                    stringResource(id = R.string.usage),
                    fontSize = 25.sp,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(300.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            //Button which navigates you to the openSource composable
            Button(
                onClick = { navController.navigate("openSource") },
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(size = 75.dp))
                    .height(70.dp)
                    .fillMaxSize()
            ) {
                Text(
                    stringResource(id = R.string.opensource),
                    fontSize = 25.sp,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(300.dp)
                )
            }

            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}

@Composable
fun OpenSource(modifier: Modifier = Modifier) {
    //Uses the function from AboutLibraries to display the sources
    LibrariesContainer(modifier.fillMaxSize())
}

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
        paragraphs.forEachIndexed { _, paragraph ->
            Text(
                text = paragraph,
                fontSize = 15.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(bottom = 20.dp)
            )
        }
    }
}
