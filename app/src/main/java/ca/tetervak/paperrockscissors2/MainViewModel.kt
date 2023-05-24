package ca.tetervak.paperrockscissors2

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ca.tetervak.paperrockscissors2.model.Choice
import ca.tetervak.paperrockscissors2.model.GameService
import ca.tetervak.paperrockscissors2.model.GameServiceImpl
import ca.tetervak.paperrockscissors2.screens.Screen

class MainViewModel : ViewModel() {

    private val _gameUiState: MutableState<GameUiState> =
        mutableStateOf(GameUiState())

    val gameUiSate: State<GameUiState> = _gameUiState

    private val gameService: GameService = GameServiceImpl()

    fun updateUserChoice(userChoice: Choice) {
        val uiState = gameUiSate.value
        val newUiState = uiState.copy(userChoice = userChoice)
        _gameUiState.value = newUiState
    }

    fun onPlay() {
        val uiState = gameUiSate.value
        val computerChoice = gameService.getRandomChoice()
        val newUiState = uiState.copy(
            computerChoice = computerChoice,
            gameResult = gameService.getGameResult(
                userChoice = uiState.userChoice,
                computerChoice = computerChoice
            ),
            screen = Screen.OUTPUT
        )
        _gameUiState.value = newUiState
    }

    fun onReplay() {
        val uiState = gameUiSate.value
        val newUiState = uiState.copy(screen = Screen.INPUT)
        _gameUiState.value = newUiState
    }
}