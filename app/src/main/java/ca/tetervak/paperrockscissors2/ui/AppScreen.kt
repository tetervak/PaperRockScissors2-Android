package ca.tetervak.paperrockscissors2.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ca.tetervak.paperrockscissors2.ui.input.InputScreen
import ca.tetervak.paperrockscissors2.ui.output.OutputScreen
import ca.tetervak.paperrockscissors2.ui.theme.AppTheme


@Composable
fun AppScreen() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val viewModel: GameViewModel = viewModel()
            val uiState: GameUiState by viewModel.uiStateFlow.collectAsState()

            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "input"
            ) {
                composable(route = "input") {
                    InputScreen(
                        userChoice = uiState.userChoice,
                        onUserChoiceChange = {
                            viewModel.updateUserChoice(it)
                        },
                        onPlay = {
                            viewModel.onPlay()
                            navController.navigate(route = "output")
                        }
                    )
                }
                composable(route = "output") {
                    OutputScreen(
                        userChoice = uiState.userChoice,
                        computerChoice = uiState.computerChoice,
                        gameResult = uiState.gameResult,
                        onReplay = {
                            viewModel.onReplay()
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}