package ca.tetervak.paperrockscissors2.domain

import ca.tetervak.paperrockscissors2.data.service.GameService
import javax.inject.Inject

class GetGameResultUseCase @Inject constructor(
    private val service: GameService
) {
    operator fun invoke(
        userChoice: Choice, computerChoice: Choice
    ): GameResult = service.getGameResult(
        userChoice = userChoice, computerChoice = computerChoice
    )
}