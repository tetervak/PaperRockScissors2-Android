package ca.tetervak.paperrockscissors2.ui.common

import androidx.annotation.StringRes
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

@Composable
fun GameButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    @StringRes stringRes: Int,
    modifier: Modifier = Modifier
) {
    Button(onClick = onClick, modifier = modifier) {
        Icon(
            imageVector = imageVector, contentDescription = null
        )
        Text(
            text = stringResource(stringRes), fontSize = 20.sp
        )
    }
}