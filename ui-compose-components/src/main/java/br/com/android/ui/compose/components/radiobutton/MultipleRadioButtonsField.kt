package br.com.android.ui.compose.components.radiobutton

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.android.ui.compose.components.layout.ResponsiveGridFlowLayout
import br.com.android.ui.compose.components.radiobutton.state.MultipleRadioButtonsState
import kotlin.collections.forEach

/**
 * Um campo que exibe múltiplos [LabeledRadioButton] organizados em um layout de grade responsivo.
 *
 * Utiliza o [ResponsiveGridFlowLayout] para distribuir os radio buttons de forma
 * inteligente, respeitando o número máximo de colunas definido no estado.
 *
 * @param state O estado que gerencia a coleção de radio buttons e suas interações.
 * @param modifier O [Modifier] a ser aplicado ao layout do container.
 *
 * @see [MultipleRadioButtonsState]
 * @see [LabeledRadioButton]
 * @see [ResponsiveGridFlowLayout]
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun MultipleRadioButtonsField(
    state: MultipleRadioButtonsState,
    modifier: Modifier = Modifier
) {
    ResponsiveGridFlowLayout(
        maxColumns = state.maxColumns,
        modifier = modifier,
        content = {
            state.radioButtons.forEach { radioButtonState ->
                LabeledRadioButton(
                    state = radioButtonState,
                    onClick = {
                        state.onRadioButtonClick(radioButtonState.identifier)
                    }
                )
            }
        }
    )
}