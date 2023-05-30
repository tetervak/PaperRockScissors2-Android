package ca.tetervak.paperrockscissors2

import androidx.lifecycle.ViewModel
import ca.tetervak.paperrockscissors2.model.Choice
import ca.tetervak.paperrockscissors2.model.GameService
import ca.tetervak.paperrockscissors2.model.GameServiceImpl
import ca.tetervak.paperrockscissors2.screens.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    private val _uiStateFlow: MutableStateFlow<GameUiState> =
        MutableStateFlow(GameUiState())

    val uiStateFlow: StateFlow<GameUiState> = _uiStateFlow

    private val gameService: GameService = GameServiceImpl()

    fun updateUserChoice(userChoice: Choice) {
        _uiStateFlow.update { uiState ->
            uiState.copy(userChoice = userChoice)
        }
    }

    fun onPlay() {
        val computerChoice = gameService.getRandomChoice()
        _uiStateFlow.update { uiState ->
            uiState.copy(
                computerChoice = computerChoice,
                gameResult = gameService.getGameResult(
                    userChoice = uiState.userChoice,
                    computerChoice = computerChoice
                ),
                screen = Screen.OUTPUT
            )
        }
    }

    fun onReplay() {
        _uiStateFlow.update { uiState ->
            uiState.copy(screen = Screen.INPUT)
        }
    }
}