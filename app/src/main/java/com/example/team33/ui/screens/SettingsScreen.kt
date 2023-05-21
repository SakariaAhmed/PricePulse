package com.example.team33.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.team33.R
import com.example.team33.network.ElectricityRegion
import com.example.team33.ui.uistates.MainUiState
import com.mikepenz.aboutlibraries.ui.compose.LibrariesContainer

@Composable
fun SettingsScreen(
    mainUiState: MainUiState,
    navController: NavHostController,
    changeElectricityRegion: (ElectricityRegion) -> Unit,
    modifier: Modifier = Modifier
) {

    //
    val openDialog = remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {

        // Radio buttons for regions
        
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = { openDialog.value = !openDialog.value }) {
            Text(stringResource(R.string.select_region))
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

            Text(text = stringResource(id = R.string.language))
            Text(text = stringResource(id = R.string.change_language))

            Button(
                onClick = { navController.navigate("showDeveloper") },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(stringResource(id = R.string.developers))
            }

            Button(
                onClick = { navController.navigate("showPurpose") },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(stringResource(id = R.string.usage))
            }

            Button(
                onClick = { navController.navigate("openSource") },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(stringResource(id = R.string.opensource))
            }

        Spacer(modifier = Modifier.padding(10.dp))


    }
    //

}


@Composable
fun OpenSource(modifier: Modifier = Modifier) {
    LibrariesContainer(modifier.fillMaxSize())
}

@Composable
fun ShowDevelopers(modifier: Modifier = Modifier) {
    val array: Array<String> = stringArrayResource(id = R.array.names)
    Column(modifier = modifier) {
        Text(text = stringResource(id = R.string.made_by))
        array.forEach { selectedOption ->
            Text(text = selectedOption)
        }
    }
}

@Composable
fun ShowPurpose(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.purpose), modifier = modifier
    )
}


