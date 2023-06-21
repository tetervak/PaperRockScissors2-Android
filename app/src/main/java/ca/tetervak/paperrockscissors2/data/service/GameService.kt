package ca.tetervak.paperrockscissors2.data.service

import ca.tetervak.paperrockscissors2.domain.Choice
import ca.tetervak.paperrockscissors2.domain.GameResult

interface GameService {

    fun getGameResult(userChoice: Choice, computerChoice: Choice): GameResult

    fun getRandomChoice(): Choice
}