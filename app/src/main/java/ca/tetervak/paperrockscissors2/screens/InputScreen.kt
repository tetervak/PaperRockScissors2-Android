package ca.tetervak.paperrockscissors2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.tetervak.paperrockscissors2.R
import ca.tetervak.paperrockscissors2.model.Choice
import ca.tetervak.paperrockscissors2.ui.theme.PaperRockScissorsTheme

@Composable
fun InputScreen(
    userChoice: Choice,
    onUserChoiceChange: (Choice) -> Unit,
    onPlay: () -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(32.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 24.sp,
            color = colorResource(id = R.color.pink_800)
        )
        Text(
            text = stringResource(R.string.make_your_choice),
            fontSize = 20.sp,
            color = colorResource(id = R.color.green_800)
        )
        UserChoiceInput(
            userChoice = userChoice,
            onChange = onUserChoiceChange
        )
        Button(onClick = onPlay) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.play),
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun UserChoiceInput(
    userChoice: Choice,
    onChange: (Choice) -> Unit
) {
    Column() {
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
    PaperRockScissorsTheme {
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