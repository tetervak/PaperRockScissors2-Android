package ca.tetervak.paperrockscissors2.ui.navigation

import ca.tetervak.paperrockscissors2.R

object OutputDestination: NavDestination {
    override val route: String = "output"
    override val titleRes: Int = R.string.game_result
    const val userChoiceArg: String = "user_choice"
    val routeWithArgs = "$route/{$userChoiceArg}"
}