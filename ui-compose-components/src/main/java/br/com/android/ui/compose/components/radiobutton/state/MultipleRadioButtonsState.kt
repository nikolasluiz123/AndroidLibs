package br.com.android.ui.compose.components.radiobutton.state

/**
 * Representa o estado para um grupo de radio buttons gerenciado pelo [br.com.android.ui.compose.components.radiobutton.MultipleRadioButtonsField].
 *
 * @property radioButtons A lista de estados individuais para cada [RadioButtonState].
 * @property onRadioButtonClick A função de callback invocada quando qualquer radio button do grupo é clicado. Recebe o [Enum] identificador do item selecionado.
 * @property maxColumns O número máximo de colunas para organizar os radio buttons no layout.
 *
 * @author Nikolas Luiz Schmitt
 */
data class MultipleRadioButtonsState(
    val radioButtons: List<RadioButtonState> = emptyList(),
    val onRadioButtonClick: (Enum<*>) -> Unit = { },
    val maxColumns: Int = 3
)