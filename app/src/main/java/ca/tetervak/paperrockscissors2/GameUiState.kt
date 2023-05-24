package ca.tetervak.paperrockscissors2

import ca.tetervak.paperrockscissors2.model.Choice
import ca.tetervak.paperrockscissors2.model.GameResult
import ca.tetervak.paperrockscissors2.screens.Screen

data class GameUiState(
    val screen: Screen = Screen.INPUT,
    val userChoice: Choice = Choice.ROCK,
    val computerChoice: Choice = Choice.ROCK,
    val gameResult: GameResult = GameResult.REPLAY
)
