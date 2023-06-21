package ca.tetervak.paperrockscissors2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ca.tetervak.paperrockscissors2.domain.Choice
import ca.tetervak.paperrockscissors2.ui.input.InputScreen
import ca.tetervak.paperrockscissors2.ui.input.InputUiState
import ca.tetervak.paperrockscissors2.ui.input.InputViewModel
import ca.tetervak.paperrockscissors2.ui.output.OutputScreen
import ca.tetervak.paperrockscissors2.ui.output.OutputUiState
import ca.tetervak.paperrockscissors2.ui.output.OutputViewModel

@Composable
fun GameNavHost(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = InputDestination.route
    ) {
        composable(route = InputDestination.route) {
            val inputViewModel: InputViewModel = hiltViewModel()
            val state: State<InputUiState> = inputViewModel.inputUiState.collectAsState()
            val inputUiState: InputUiState by state
            InputScreen(userChoice = inputUiState.userChoice, onUserChoiceChange = {
                inputViewModel.updateUserChoice(it)
            }, onPlay = {
                navController.navigate(route = "${OutputDestination.route}/${inputUiState.userChoice}")
            })
        }
        composable(
            route = OutputDestination.routeWithArgs,
            arguments = listOf(navArgument(OutputDestination.userChoiceArg) {
                type = NavType.EnumType(Choice::class.java)
            })
        ) {
            val outputViewModel: OutputViewModel = hiltViewModel()
            val outputUiState: OutputUiState = outputViewModel.outputUiState
            OutputScreen(userChoice = outputUiState.userChoice,
                computerChoice = outputUiState.computerChoice,
                gameResult = outputUiState.gameResult,
                onReplay = {
                    navController.popBackStack()
                })
        }
    }
}