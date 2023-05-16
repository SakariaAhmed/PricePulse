package com.example.team33.ui.screens

import android.annotation.SuppressLint
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

@SuppressLint("UnrememberedMutableState")
@Composable
fun SettingsScreen(
    mainUiState: MainUiState,
    navController: NavHostController,
    changeElectricityRegion: (ElectricityRegion) -> Unit
) {
    val regionOptions: List<String> = listOf(
        stringResource(id = R.string.east_norway),
        stringResource(id = R.string.south_norway),
        stringResource(id = R.string.mid_norway),
        stringResource(id = R.string.north_norway),
        stringResource(id = R.string.west_norway)
    )
    var east = remember { mutableStateOf(true) }
    var south = remember { mutableStateOf(false) }
    var mid = remember { mutableStateOf(false) }
    var north = remember { mutableStateOf(false) }
    var west = remember { mutableStateOf(false) }

    val checkRegion = listOf(east, south, mid, north, west)


    val current = remember { mutableStateOf(mainUiState.currentRegion)  }

    val openDialog = remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {

        // Dialog box for regions


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
                    Column() {


                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = regionOptions[0])
                            Checkbox(
                                checked = east.value,
                                onCheckedChange = {
                                    east.value = !east.value; SetFalse(
                                    checkRegion,
                                    east
                                );current.value = ElectricityRegion.NO1
                                })
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = regionOptions[1])
                            Checkbox(
                                checked = south.value,
                                onCheckedChange = {
                                    south.value = !south.value;SetFalse(
                                    checkRegion,
                                    south
                                );current.value = ElectricityRegion.NO2
                                })
                        }

                        Spacer(modifier = Modifier.height(20.dp))


                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = regionOptions[2])
                            Checkbox(
                                checked = mid.value,
                                onCheckedChange = {
                                    mid.value = !mid.value; SetFalse(
                                    checkRegion,
                                    mid
                                );current.value = ElectricityRegion.NO3
                                })
                        }

                        Spacer(modifier = Modifier.height(20.dp))


                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = regionOptions[3])
                            Checkbox(
                                checked = north.value,
                                onCheckedChange = {
                                    north.value = !north.value;SetFalse(
                                    checkRegion,
                                    north
                                );current.value = ElectricityRegion.NO4
                                })
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = regionOptions[4])
                            Checkbox(
                                checked = west.value,
                                onCheckedChange = {
                                    west.value = !west.value; SetFalse(
                                    checkRegion,
                                    west
                                );current.value = ElectricityRegion.NO5
                                })
                        }

                    }
                },

                confirmButton = {
                    Button(

                        onClick = {
                            openDialog.value = false;
                            var answer: MutableState<Boolean> =
                                mutableStateOf(true)

                            for (r in checkRegion) {
                                if (r.value) {
                                    answer =r
                                }
                            }

                            SetFalse(checkRegion, answer   )
                            changeElectricityRegion(current.value)
                        }) {
                        Text("Ok")
                    }
                }
            )


        }



        Text(text = stringResource(id = R.string.language))
        Text(text = stringResource(id = R.string.change_language))

        Button(
            onClick = { navController.navigate("showDeveloper") }, modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text(stringResource(id = R.string.show_developers))
        }


        Button(
            onClick = { navController.navigate("showPurpose") }, modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text(stringResource(id = R.string.show_usage))
        }

        Button(onClick = { navController.navigate("openSource") }) {
            Text(stringResource(id = R.string.opensource))
        }
    }
}


fun SetFalse(checkRegion: List<MutableState<Boolean>>, region: MutableState<Boolean>) {
    for (r in checkRegion) {
        r.value = false
    }
    region.value = true
}

@Composable
fun OpenSource() {
    LibrariesContainer(Modifier.fillMaxSize())
}

@Composable
fun ShowDevelopers() {
    val array: Array<String> = stringArrayResource(id = R.array.names)
    Column(modifier = Modifier) {
        Text(text = stringResource(id = R.string.made_by))
        array.forEach { selectedOption ->
            Text(text = selectedOption)
        }
    }
}

@Composable
fun ShowPurpose() {
    Text(text = stringResource(id = R.string.purpose))
}

