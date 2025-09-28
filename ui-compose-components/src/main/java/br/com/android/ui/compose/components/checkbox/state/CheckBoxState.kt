package br.com.android.ui.compose.components.checkbox.state

/**
 * Representa o estado de um único [androidx.compose.material3.Checkbox] dentro de um formulário.
 *
 * @property label O texto a ser exibido ao lado do checkbox.
 * @property identifier Um enum que identifica unicamente o checkbox.
 * @property checked `true` se o checkbox estiver marcado, `false` caso contrário.
 * @property enabled `true` se o checkbox estiver habilitado para interação, `false` caso contrário.
 *
 * @author Nikolas Luiz Schmitt
 */
data class CheckBoxState(
    val label: String,
    val identifier: Enum<*>,
    val checked: Boolean = false,
    val enabled: Boolean = true
)