package ca.tetervak.paperrockscissors2.ui.input

import androidx.lifecycle.ViewModel
import ca.tetervak.paperrockscissors2.domain.Choice
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class InputViewModel @Inject constructor(
) : ViewModel() {

    private val _inputUiState: MutableStateFlow<InputUiState> =
        MutableStateFlow(InputUiState())
    val inputUiState: StateFlow<InputUiState> = _inputUiState

    fun updateUserChoice(choice: Choice) {
        _inputUiState.update { uiState ->
            uiState.copy(userChoice = choice)
        }
    }
}