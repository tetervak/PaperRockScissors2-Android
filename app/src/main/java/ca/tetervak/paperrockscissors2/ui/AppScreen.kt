package ca.tetervak.paperrockscissors2.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ca.tetervak.paperrockscissors2.domain.Choice
import ca.tetervak.paperrockscissors2.ui.input.InputScreen
import ca.tetervak.paperrockscissors2.ui.input.InputUiState
import ca.tetervak.paperrockscissors2.ui.input.InputViewModel
import ca.tetervak.paperrockscissors2.ui.output.OutputScreen
import ca.tetervak.paperrockscissors2.ui.output.OutputUiState
import ca.tetervak.paperrockscissors2.ui.output.OutputViewModel
import ca.tetervak.paperrockscissors2.ui.theme.AppTheme


@Composable
fun AppScreen() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
            NavHost(
                navController = navController, startDestination = "input"
            ) {
                composable(route = "input") {
                    val inputViewModel: InputViewModel = hiltViewModel()
                    val state: State<InputUiState> = inputViewModel.inputUiState.collectAsState()
                    val inputUiState: InputUiState by state
                    InputScreen(userChoice = inputUiState.userChoice, onUserChoiceChange = {
                        inputViewModel.updateUserChoice(it)
                    }, onPlay = {
                        navController.navigate(route = "output/${inputUiState.userChoice}")
                    })
                }
                composable(
                    route = "output/{user_choice}",
                    arguments = listOf(navArgument("user_choice") {
                        type = NavType.EnumType(Choice::class.java)
                    })
                ) {
                    val outputViewModel: OutputViewModel = hiltViewModel()
                    val outputUiState: OutputUiState = outputViewModel.outputUiState
                    OutputScreen(
                        userChoice = outputUiState.userChoice,
                        computerChoice = outputUiState.computerChoice,
                        gameResult = outputUiState.gameResult,
                        onReplay = {
                            navController.popBackStack()
                        })
                }
            }
        }
    }
}