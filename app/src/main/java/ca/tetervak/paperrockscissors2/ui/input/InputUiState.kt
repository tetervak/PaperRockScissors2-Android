package ca.tetervak.paperrockscissors2.ui.input

import ca.tetervak.paperrockscissors2.domain.Choice

data class InputUiState(
    val userChoice: Choice = Choice.ROCK
)
