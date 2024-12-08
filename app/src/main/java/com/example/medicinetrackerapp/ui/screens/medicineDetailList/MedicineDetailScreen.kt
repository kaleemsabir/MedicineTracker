package com.example.medicinetrackerapp.ui.screens.medicineDetailList

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun MedicineDetailScreen(navController: NavController, medicineId: Int, viewModel: MedicineDetailViewModel = hiltViewModel()) {
    // Fetch the medicine details
    viewModel.fetchMedicineDetail(medicineId)

    // Collect the medicine details state
    val medicineDetail by viewModel.medicineDetail.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .safeDrawingPadding()
    ) {
        // Show loading indicator while fetching data
        if (medicineDetail == null) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        } else {
            // Display medicine details
            Text(text = "Name: ${medicineDetail?.name}", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Dose: ${medicineDetail?.dose}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Strength: ${medicineDetail?.strength}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text("Back")
            }
        }
    }
}
