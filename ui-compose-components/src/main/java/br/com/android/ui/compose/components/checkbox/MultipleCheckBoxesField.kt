package br.com.android.ui.compose.components.checkbox

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.android.ui.compose.components.layout.ResponsiveGridFlowLayout
import br.com.android.ui.compose.components.checkbox.state.MultipleCheckBoxesState
import kotlin.collections.forEach

/**
 * Um campo que exibe múltiplos [LabeledCheckBox] organizados em um layout de grade responsivo.
 *
 * @param state O estado do campo, que contém a lista de checkboxes e a ação de clique.
 * @param modifier O [Modifier] a ser aplicado ao [ResponsiveGridFlowLayout].
 *
 * @see [MultipleCheckBoxesState]
 * @see [LabeledCheckBox]
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun MultipleCheckBoxesField(
    state: MultipleCheckBoxesState,
    modifier: Modifier = Modifier
) {
    ResponsiveGridFlowLayout(
        maxColumns = state.maxColumns,
        modifier = modifier,
        content = {
            state.checkBoxes.forEach { checkBoxState ->
                LabeledCheckBox(
                    state = checkBoxState,
                    onClick = {
                        state.onCheckBoxClick(checkBoxState.identifier)
                    }
                )
            }
        }
    )
}