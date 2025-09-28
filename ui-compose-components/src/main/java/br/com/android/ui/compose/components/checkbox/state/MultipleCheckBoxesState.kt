package br.com.android.ui.compose.components.checkbox.state

/**
 * Representa o estado de um grupo de checkboxes exibidos juntos.
 *
 * @property checkBoxes A lista de [CheckBoxState] a serem exibidos.
 * @property onCheckBoxClick A função a ser chamada quando qualquer um dos checkboxes for clicado. Recebe o [CheckBoxState.identifier] como parâmetro.
 * @property maxColumns O número máximo de colunas para o layout de grade.
 *
 * @author Nikolas Luiz Schmitt
 */
data class MultipleCheckBoxesState(
    val checkBoxes: List<CheckBoxState> = emptyList(),
    val onCheckBoxClick: (Enum<*>) -> Unit = { },
    val maxColumns: Int = 3
)