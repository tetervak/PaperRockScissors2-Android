package ca.tetervak.paperrockscissors2.ui

import androidx.lifecycle.ViewModel
import ca.tetervak.paperrockscissors2.domain.Choice
import ca.tetervak.paperrockscissors2.domain.GetComputerChoiceUseCase
import ca.tetervak.paperrockscissors2.domain.GetGameResultUseCase
import ca.tetervak.paperrockscissors2.domain.GetRandomChoiceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val getRandomChoiceUseCase: GetRandomChoiceUseCase,
    private val getComputerChoiceUseCase: GetComputerChoiceUseCase,
    private val getGameResultUseCase: GetGameResultUseCase
) : ViewModel() {

    private val _uiStateFlow: MutableStateFlow<GameUiState> = MutableStateFlow(
        GameUiState(
            userChoice = getRandomChoiceUseCase()
        )
    )

    val uiStateFlow: StateFlow<GameUiState> = _uiStateFlow

    fun updateUserChoice(userChoice: Choice) {
        _uiStateFlow.update { uiState ->
            uiState.copy(userChoice = userChoice)
        }
    }

    fun onPlay() {
        val computerChoice = getComputerChoiceUseCase()
        _uiStateFlow.update { uiState ->
            uiState.copy(
                computerChoice = computerChoice, gameResult = getGameResultUseCase(
                    userChoice = uiState.userChoice, computerChoice = computerChoice
                )
            )
        }
    }

    fun onReplay() {
        _uiStateFlow.update { uiState ->
            uiState.copy(
                userChoice = getRandomChoiceUseCase()
            )
        }
    }
}