package com.example.medicinetrackerapp.ui.navHost

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.medicinetrackerapp.ui.screens.login.LoginScreen
import com.example.medicinetrackerapp.ui.screens.medicineDetailList.MedicineDetailScreen
import com.example.medicinetrackerapp.ui.screens.medicineList.MedicineScreen


object Destinations {
    const val LOGIN_SCREEN_ROUTE = "LoginScreen"
    const val LOGIN_ROUTE = "LoginRoute"
}


@Composable
fun MedicineTrackerNavHost(navController: NavHostController = rememberNavController(),
               snackbarHostState : SnackbarHostState) {
    val startDestination = Destinations.LOGIN_ROUTE
    NavHost(
        navController = navController,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> fullWidth }, // Slide in from right
                animationSpec = tween(300) // Try a shorter duration
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> -fullWidth }, // Slide out to left
                animationSpec = tween(300) // Match the duration
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> -fullWidth }, // Slide in from left when popping
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> fullWidth }, // Slide out to right when popping
                animationSpec = tween(300)
            )
        },
        startDestination = startDestination) {

        loginRoute(navController)

    }
}


fun NavGraphBuilder.loginRoute(navController: NavHostController) {
    navigation(startDestination = Destinations.LOGIN_SCREEN_ROUTE, route = Destinations.LOGIN_ROUTE) {
        composable(Destinations.LOGIN_SCREEN_ROUTE) {
            LoginScreen(navController = navController)
        }
        composable(
          "medicineListRoute/{userName}"
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: ""
            MedicineScreen(navController = navController, userName = userName)
        }
        composable("medicineDetail/{medicineId}") { backStackEntry ->
            val medicineId = backStackEntry.arguments?.getString("medicineId")?.toIntOrNull() ?: return@composable
            MedicineDetailScreen(navController = navController, medicineId = medicineId)
        }
    }
}
