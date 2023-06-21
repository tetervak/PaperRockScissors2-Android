package ca.tetervak.paperrockscissors2.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ca.tetervak.paperrockscissors2.ui.navigation.GameNavHost

@Composable
fun AppScreen(navController: NavHostController = rememberNavController()) {
    GameNavHost(navController)
}