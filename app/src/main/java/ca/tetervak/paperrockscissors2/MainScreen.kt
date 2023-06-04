package ca.tetervak.paperrockscissors2

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.tetervak.paperrockscissors2.screens.InputScreen
import ca.tetervak.paperrockscissors2.screens.OutputScreen
import ca.tetervak.paperrockscissors2.screens.Screen
import ca.tetervak.paperrockscissors2.ui.theme.PaperRockScissorsTheme


@Composable
fun MainScreen() {
    PaperRockScissorsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val viewModel: MainViewModel = viewModel()
            val uiState: GameUiState by viewModel.uiState

            when (uiState.screen) {
                Screen.INPUT -> InputScreen(
                    userChoice = uiState.userChoice,
                    onUserChoiceChange = { viewModel.updateUserChoice(it) },
                    onPlay = { viewModel.onPlay() }
                )

                Screen.OUTPUT -> OutputScreen(
                    userChoice = uiState.userChoice,
                    computerChoice = uiState.computerChoice,
                    gameResult = uiState.gameResult,
                    onReplay = { viewModel.onReplay() }
                )
            }
        }
    }
}