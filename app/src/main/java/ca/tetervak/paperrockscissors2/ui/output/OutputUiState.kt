package ca.tetervak.paperrockscissors2.ui.output

import ca.tetervak.paperrockscissors2.domain.Choice
import ca.tetervak.paperrockscissors2.domain.GameResult

data class OutputUiState(
    val userChoice: Choice, val computerChoice: Choice, val gameResult: GameResult
)