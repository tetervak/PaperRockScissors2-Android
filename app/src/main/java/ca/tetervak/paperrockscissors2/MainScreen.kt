package ca.tetervak.paperrockscissors2

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
//import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ca.tetervak.paperrockscissors2.screens.InputScreen
import ca.tetervak.paperrockscissors2.screens.OutputScreen
import ca.tetervak.paperrockscissors2.ui.theme.MainTheme


@Composable
fun MainScreen() {
    MainTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val viewModel: MainViewModel = viewModel()
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
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}