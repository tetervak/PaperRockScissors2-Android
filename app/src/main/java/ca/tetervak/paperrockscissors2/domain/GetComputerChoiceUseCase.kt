package ca.tetervak.paperrockscissors2.domain

import javax.inject.Inject

class GetComputerChoiceUseCase @Inject constructor(
    private val getRandomChoiceUseCase: GetRandomChoiceUseCase
) {
    operator fun invoke(): Choice = getRandomChoiceUseCase()
}