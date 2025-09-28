package br.com.android.ui.compose.components.checkbox

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import br.com.android.ui.compose.components.styles.LabelTextStyle
import br.com.android.ui.compose.components.checkbox.state.CheckBoxState

/**
 * Um [Composable] que exibe um [Checkbox] com um rótulo de texto ao lado.
 * A interação com o componente (clique) aciona a função [onClick].
 *
 * @param state O estado do checkbox, contendo o rótulo, se está marcado e se está habilitado.
 * @param onClick A função a ser chamada quando o [LabeledCheckBox] for clicado.
 * @param modifier O [Modifier] a ser aplicado ao contêiner do [Row].
 * @param textStyle O estilo do texto a ser aplicado ao rótulo.
 *
 * @see [CheckBoxState]
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun LabeledCheckBox(
    state: CheckBoxState,
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
        Checkbox(
            checked = state.checked,
            onCheckedChange = { onClick() },
            enabled = state.enabled,
        )
        Text(text = state.label, style = textStyle)
    }
}