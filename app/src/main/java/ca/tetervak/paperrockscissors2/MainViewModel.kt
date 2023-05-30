package ca.tetervak.paperrockscissors2

import androidx.lifecycle.ViewModel
import ca.tetervak.paperrockscissors2.model.Choice
import ca.tetervak.paperrockscissors2.model.GameService
import ca.tetervak.paperrockscissors2.model.GameServiceImpl
import ca.tetervak.paperrockscissors2.screens.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private val _uiStateFlow: MutableStateFlow<GameUiState> =
        MutableStateFlow(GameUiState())

    val uiStateFlow: StateFlow<GameUiState> = _uiStateFlow

    private val gameService: GameService = GameServiceImpl()

    fun updateUserChoice(userChoice: Choice) {
        val uiState = uiStateFlow.value
        val newUiState = uiState.copy(userChoice = userChoice)
        _uiStateFlow.value = newUiState
    }

    fun onPlay() {
        val uiState = uiStateFlow.value
        val computerChoice = gameService.getRandomChoice()
        val newUiState = uiState.copy(
            computerChoice = computerChoice,
            gameResult = gameService.getGameResult(
                userChoice = uiState.userChoice,
                computerChoice = computerChoice
            ),
            screen = Screen.OUTPUT
        )
        _uiStateFlow.value = newUiState
    }

    fun onReplay() {
        val uiState = uiStateFlow.value
        val newUiState = uiState.copy(screen = Screen.INPUT)
        _uiStateFlow.value = newUiState
    }
}