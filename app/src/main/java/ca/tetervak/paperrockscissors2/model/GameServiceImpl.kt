package ca.tetervak.paperrockscissors2.model

import kotlin.random.Random

class GameServiceImpl(private val random: Random): GameService {

    constructor():this(Random.Default)

    override fun getRandomChoice(): Choice =
        Choice.values().random(random)

    override fun getGameResult(
        userChoice: Choice,
        computerChoice: Choice
    ): GameResult =
        when (userChoice) {
            Choice.PAPER -> when (computerChoice) {
                Choice.PAPER -> GameResult.REPLAY
                Choice.ROCK -> GameResult.USER_WINS
                Choice.SCISSORS -> GameResult.COMPUTER_WINS
            }
            Choice.ROCK -> when (computerChoice) {
                Choice.PAPER -> GameResult.COMPUTER_WINS
                Choice.ROCK -> GameResult.REPLAY
                Choice.SCISSORS -> GameResult.USER_WINS
            }
            Choice.SCISSORS -> when (computerChoice) {
                Choice.PAPER -> GameResult.USER_WINS
                Choice.ROCK -> GameResult.COMPUTER_WINS
                Choice.SCISSORS -> GameResult.REPLAY
            }
        }
}