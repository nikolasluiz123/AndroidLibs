package br.com.android.ui.compose.components.radiobutton

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import br.com.android.ui.compose.components.styles.LabelTextStyle
import br.com.android.ui.compose.components.radiobutton.state.RadioButtonState

/**
 * Um [RadioButton] que inclui um rótulo de texto.
 *
 * Este componente agrupa um [RadioButton] e um [Text] em uma [Row], alinhando-os
 * verticalmente. A interação de clique é gerenciada para toda a área da linha,
 * melhorando a usabilidade.
 *
 * @param state O estado que define as propriedades do radio button.
 * @param onClick A função a ser executada quando o componente for clicado.
 * @param modifier O [Modifier] a ser aplicado ao layout da [Row].
 * @param textStyle O estilo a ser aplicado ao texto do rótulo.
 *
 * @see [RadioButtonState]
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun LabeledRadioButton(
    state: RadioButtonState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LabelTextStyle
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable(enabled = state.enabled) {
                onClick()
            }
    ) {
        RadioButton(selected = state.selected, enabled = state.enabled, onClick = onClick)
        Text(text = state.label, style = textStyle)
    }
}