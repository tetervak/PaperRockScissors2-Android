package ca.tetervak.paperrockscissors2.model

interface GameService {

    fun getGameResult(userChoice: Choice, computerChoice: Choice): GameResult

    fun getRandomChoice(): Choice
}