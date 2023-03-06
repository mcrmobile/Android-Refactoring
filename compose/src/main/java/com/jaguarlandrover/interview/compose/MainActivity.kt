package com.jaguarlandrover.interview.compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var vehicles by remember { mutableStateOf(emptyList<Vehicle>()) }
            LaunchedEffect(Unit) {
                try {
                    vehicles = DependencyContainer.vehiclesService.getVehicles()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            MaterialTheme {
                LazyColumn {
                    // My Vehicles title
                    item {
                        Text(
                            text = "My Vehicles",
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.h1,
                            textAlign = TextAlign.Center
                        )
                    }

                    // List of vehicles
                    items(vehicles.size) {
                        Text(
                            modifier = Modifier
                                .padding(24.dp)
                                .clickable {
                                    Toast
                                        .makeText(
                                            this@MainActivity,
                                            vehicles[it].model,
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                },
                            text = vehicles[it].brand + " " + vehicles[it].model
                        )
                    }
                }
            }

        }
    }
}