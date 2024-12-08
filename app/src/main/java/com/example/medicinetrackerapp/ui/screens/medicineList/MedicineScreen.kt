package com.example.medicinetrackerapp.ui.screens.medicineList

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medicinetrackerapp.data.model.Medicine
import com.example.medicinetrackerapp.R

@Composable
fun MedicineScreen(
    navController: NavController,
    userName: String,
    viewModel: MedicineViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    BackHandler {
        // Do nothing to disable back press
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .safeDrawingPadding()
    ) {
        Text(
            text = "Welcome, $userName!",
            style = MaterialTheme.typography.headlineMedium
        )

        when (uiState) {
            is MedicineUIState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }

            is MedicineUIState.Success -> {
                val medicines = (uiState as MedicineUIState.Success).medicines
                LazyColumn {
                    items(medicines) { medicine ->
                        MedicineCard(medicine, onClick = {
                            navController.navigate("medicineDetail/${medicine.id}")
                        })
                    }
                }
            }

            is MedicineUIState.Error -> {
                Text(
                    text = "Error: ${(uiState as MedicineUIState.Error).message}",
                    color = MaterialTheme.colorScheme.error
                )
            }

            is MedicineUIState.Idle -> {
                // Optional: Handle idle state (e.g., display a placeholder)
            }
        }
    }
}

@Composable
fun MedicineCard(medicine: Medicine, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${medicine.name}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Dose: ${medicine.dose}", style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "Strength: ${medicine.strength}",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    // Navigate to the medicine detail screen and pass the id
                    onClick()
                }
            ) {
                Text(stringResource(R.string.view_details))
            }
        }
    }
}