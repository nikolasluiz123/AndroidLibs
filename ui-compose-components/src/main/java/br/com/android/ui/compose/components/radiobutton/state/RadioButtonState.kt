package br.com.android.ui.compose.components.radiobutton.state

/**
 * Representa o estado de um único [br.com.android.ui.compose.components.radiobutton.LabeledRadioButton].
 *
 * @property label O texto a ser exibido ao lado do radio button.
 * @property identifier Um identificador único, geralmente um [Enum], para distinguir este radio button dos outros em um grupo.
 * @property selected Indica se o radio button está atualmente selecionado.
 * @property enabled Controla se o radio button pode ser interagido pelo usuário.
 *
 * @author Nikolas Luiz Schmitt
 */
data class RadioButtonState(
    val label: String,
    val identifier: Enum<*>,
    val selected: Boolean = false,
    val enabled: Boolean = true
)