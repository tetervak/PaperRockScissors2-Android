package ca.tetervak.paperrockscissors2.domain

import ca.tetervak.paperrockscissors2.data.service.GameService
import javax.inject.Inject

class GetRandomChoiceUseCase @Inject constructor(
    private val service: GameService
) {
    operator fun invoke(): Choice = service.getRandomChoice()
}