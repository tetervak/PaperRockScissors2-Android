package ca.tetervak.paperrockscissors2

import androidx.lifecycle.ViewModel
import ca.tetervak.paperrockscissors2.model.Choice
import ca.tetervak.paperrockscissors2.model.GameService
import ca.tetervak.paperrockscissors2.model.GameServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val gameService: GameService
) : ViewModel() {

    private val _uiStateFlow: MutableStateFlow<GameUiState> =
        MutableStateFlow(GameUiState())

    val uiStateFlow: StateFlow<GameUiState> = _uiStateFlow

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
                )
            )
        }
    }
}