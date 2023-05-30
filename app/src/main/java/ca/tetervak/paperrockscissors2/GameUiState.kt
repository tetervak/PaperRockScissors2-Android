package ca.tetervak.paperrockscissors2

import ca.tetervak.paperrockscissors2.model.Choice
import ca.tetervak.paperrockscissors2.model.GameResult

data class GameUiState(
    val userChoice: Choice = Choice.ROCK,
    val computerChoice: Choice = Choice.ROCK,
    val gameResult: GameResult = GameResult.REPLAY
)
