package ca.tetervak.paperrockscissors2.ui.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
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
import ca.tetervak.paperrockscissors2.ui.common.GameButton
import ca.tetervak.paperrockscissors2.ui.common.GameTopAppBar
import ca.tetervak.paperrockscissors2.ui.navigation.InputDestination
import ca.tetervak.paperrockscissors2.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen(
    userChoice: Choice,
    onUserChoiceChange: (Choice) -> Unit,
    onPlay: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
        GameTopAppBar(
            title = stringResource(InputDestination.titleRes),
            canNavigateBack = false,
            scrollBehavior = scrollBehavior
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
            Text(
                text = stringResource(R.string.make_your_choice),
                fontSize = 24.sp,
                color = colorResource(id = R.color.green_800)
            )
            UserChoiceInput(
                userChoice = userChoice,
                onChange = onUserChoiceChange
            )
            GameButton(
                onClick = onPlay,
                imageVector = Icons.Filled.Check,
                stringRes = R.string.play
            )
        }
    }
}
@Composable
fun UserChoiceInput(
    userChoice: Choice,
    onChange: (Choice) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = userChoice == Choice.PAPER,
                onClick = { onChange(Choice.PAPER) }
            )
            Text(
                text = stringResource(R.string.paper),
                fontSize = 18.sp
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = userChoice == Choice.ROCK,
                onClick = { onChange(Choice.ROCK) }
            )
            Text(
                text = stringResource(R.string.rock),
                fontSize = 18.sp
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = userChoice == Choice.SCISSORS,
                onClick = { onChange(Choice.SCISSORS) }
            )
            Text(
                text = stringResource(R.string.scissors),
                fontSize = 18.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun InputScreenPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            InputScreen(
                userChoice = Choice.ROCK,
                onUserChoiceChange = {},
                onPlay = {}
            )
        }
    }
}