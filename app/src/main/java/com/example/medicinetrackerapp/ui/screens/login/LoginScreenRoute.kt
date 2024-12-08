package com.example.medicinetrackerapp.ui.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.medicinetrackerapp.R

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    var email by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    val loginState by viewModel.loginState.collectAsState()

    // Handle login UI and navigation logic in one function
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Username input field
        TextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text(stringResource(R.string.enter_user_name)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Email input field
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.enter_email)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Login button
        Button(onClick = { viewModel.login() }) {
            Text(stringResource(R.string.login))
        }

        // Handle different login states
        when (loginState) {
            is LoginState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
            }

            is LoginState.Success -> {
                // Once login is successful, navigate to the medicine list screen
                LaunchedEffect(Unit) {
                    navController.navigate("medicineListRoute/$userName") // Navigate with username
                }
            }

            is LoginState.Error -> {
                Text(
                    text = "Error: ${(loginState as LoginState.Error).error}",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            LoginState.Idle -> {
                // Optionally handle idle state (e.g., reset UI or show a message)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}
