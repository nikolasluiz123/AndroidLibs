package br.com.android.ui.compose.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import br.com.android.ui.compose.components.styles.ButtonTextStyle
import br.com.android.ui.compose.components.styles.TextButtonTextStyle

/**
 * Botão padrão com preenchimento, seguindo o estilo primário do aplicativo.
 * Ideal para ações de alta prioridade.
 *
 * @param label O texto a ser exibido no botão.
 * @param onClickListener A lambda a ser executada quando o botão for clicado.
 * @param modifier O [Modifier] a ser aplicado ao botão.
 * @param enabled Um booleano que indica se o botão está habilitado e pode ser interagido.
 * @param textStyle O [TextStyle] a ser aplicado ao texto do botão.
 * @param colors As cores a serem usadas para o botão em diferentes estados (habilitado, desabilitado).
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun BaseButton(
    label: String,
    onClickListener: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textStyle: TextStyle = ButtonTextStyle,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
        disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
    )
) {
    Button(
        modifier = modifier,
        onClick = onClickListener,
        enabled = enabled,
        colors = colors
    ) {
        Text(
            text = label,
            style = textStyle,
        )
    }
}

/**
 * Botão com contorno, usado para ações secundárias que precisam de menos destaque
 * que o botão primário.
 *
 * @param label O texto a ser exibido no botão.
 * @param onClickListener A lambda a ser executada quando o botão for clicado.
 * @param modifier O [Modifier] a ser aplicado ao botão.
 * @param enabled Um booleano que indica se o botão está habilitado e pode ser interagido.
 * @param textStyle O [TextStyle] a ser aplicado ao texto do botão.
 * @param border A borda a ser usada para o botão.
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun BaseOutlinedButton(
    label: String,
    onClickListener: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textStyle: TextStyle = ButtonTextStyle,
    border: BorderStroke = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
) {
    OutlinedButton(
        modifier = modifier,
        border = border,
        onClick = onClickListener,
        enabled = enabled,
    ) {
        Text(
            text = label,
            style = textStyle
        )
    }
}

/**
 * Botão de texto, sem bordas ou preenchimento, usado para ações de baixa prioridade,
 * como em diálogos ou cartões.
 *
 * @param label O texto a ser exibido no botão.
 * @param onClickListener A lambda a ser executada quando o botão for clicado.
 * @param modifier O [Modifier] a ser aplicado ao botão.
 * @param colors As cores a serem usadas para o botão em diferentes estados.
 * @param enabled Um booleano que indica se o botão está habilitado e pode ser interagido.
 * @param textStyle O [TextStyle] a ser aplicado ao texto do botão.
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun BaseTextButton(
    label: String,
    onClickListener: () -> Unit,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.textButtonColors(),
    enabled: Boolean = true,
    textStyle: TextStyle = TextButtonTextStyle
) {
    TextButton(
        modifier = modifier,
        onClick = onClickListener,
        enabled = enabled,
        colors = colors
    ) {
        Text(text = label, style = textStyle)
    }
}

/**
 * Um [BaseTextButton] pré-configurado para ser usado como botão de ação em diálogos,
 * com estilos que correspondem ao tema do aplicativo para diálogos.
 *
 * @param labelResId O ID do recurso da string para o texto do botão.
 * @param onClick A lambda a ser executada quando o botão for clicado.
 * @param modifier O [Modifier] a ser aplicado ao botão.
 * @param colors As cores a serem usadas para o botão em diferentes estados.
 *
 * @see br.com.android.ui.compose.components.dialog.message.BaseMessageDialog
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun DefaultDialogTextButton(
    labelResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.textButtonColors(
        contentColor = MaterialTheme.colorScheme.onSurface,
        disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
    )
) {
    TextButton(
        modifier = modifier,
        colors = colors,
        onClick = {
            onClick()
        }
    ) {
        Text(text = stringResource(id = labelResId))
    }
}