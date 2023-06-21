package ca.tetervak.paperrockscissors2.ui

import ca.tetervak.paperrockscissors2.domain.Choice
import ca.tetervak.paperrockscissors2.domain.GameResult

data class GameUiState(
    val userChoice: Choice = Choice.ROCK,
    val computerChoice: Choice = Choice.ROCK,
    val gameResult: GameResult = GameResult.REPLAY
)
