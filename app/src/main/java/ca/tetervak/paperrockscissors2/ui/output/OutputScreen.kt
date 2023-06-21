package ca.tetervak.paperrockscissors2.ui.output

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.tetervak.paperrockscissors2.R
import ca.tetervak.paperrockscissors2.domain.Choice
import ca.tetervak.paperrockscissors2.domain.GameResult
import ca.tetervak.paperrockscissors2.ui.common.GameButton
import ca.tetervak.paperrockscissors2.ui.common.GameTopAppBar
import ca.tetervak.paperrockscissors2.ui.navigation.OutputDestination
import ca.tetervak.paperrockscissors2.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutputScreen(
    userChoice: Choice,
    computerChoice: Choice,
    gameResult: GameResult,
    onReplay: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
        GameTopAppBar(
            title = stringResource(OutputDestination.titleRes),
            canNavigateBack = true,
            scrollBehavior = scrollBehavior,
            navigateUp = onReplay
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(32.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = resultToString(gameResult = gameResult),
                    fontSize = 24.sp,
                    color = colorResource(id = R.color.orange_500)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.computer),
                        fontSize = 18.sp,
                    )
                    Text(
                        text = choiceToString(choice = computerChoice),
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.purple_500)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.you),
                        fontSize = 18.sp,
                    )
                    Text(
                        text = choiceToString(choice = userChoice),
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.purple_500)
                    )
                }
            }
            GameButton(
                onClick = onReplay,
                imageVector = Icons.Filled.ArrowBack,
                stringRes = R.string.replay
            )
        }
    }
}

@Composable
private fun choiceToString(choice: Choice): String = when (choice) {
    Choice.PAPER -> stringResource(id = R.string.paper)
    Choice.ROCK -> stringResource(id = R.string.rock)
    Choice.SCISSORS -> stringResource(id = R.string.scissors)
}

@Composable
private fun resultToString(gameResult: GameResult): String = when (gameResult) {
    GameResult.USER_WINS -> stringResource(id = R.string.you_win)
    GameResult.COMPUTER_WINS -> stringResource(id = R.string.computer_wins)
    GameResult.REPLAY -> stringResource(id = R.string.play_again)
}

@Preview(showBackground = true)
@Composable
fun OutputScreenPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            OutputScreen(userChoice = Choice.ROCK,
                computerChoice = Choice.ROCK,
                gameResult = GameResult.REPLAY,
                onReplay = {})
        }
    }
}