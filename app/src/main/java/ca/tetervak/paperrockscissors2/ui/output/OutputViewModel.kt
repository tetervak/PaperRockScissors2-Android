package ca.tetervak.paperrockscissors2.ui.output

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ca.tetervak.paperrockscissors2.domain.Choice
import ca.tetervak.paperrockscissors2.domain.GameResult
import ca.tetervak.paperrockscissors2.domain.GetComputerChoiceUseCase
import ca.tetervak.paperrockscissors2.domain.GetGameResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OutputViewModel @Inject constructor(
    getComputerChoiceUseCase: GetComputerChoiceUseCase,
    getGameResultUseCase: GetGameResultUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val outputUiState: OutputUiState

    init {
        val userChoice: Choice = savedStateHandle.get<Choice>("user_choice")!!
        val computerChoice: Choice = getComputerChoiceUseCase()
        val gameResult: GameResult = getGameResultUseCase(
            userChoice = userChoice, computerChoice = computerChoice
        )
        outputUiState = OutputUiState(
            computerChoice = computerChoice, userChoice = userChoice, gameResult = gameResult
        )
    }
}