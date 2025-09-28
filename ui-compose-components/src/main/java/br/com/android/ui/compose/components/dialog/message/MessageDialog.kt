package br.com.android.ui.compose.components.dialog.message

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import br.com.android.ui.compose.components.R
import br.com.android.ui.compose.components.buttons.DefaultDialogTextButton
import br.com.android.ui.compose.components.dialog.message.enums.EnumDialogType
import br.com.android.ui.compose.components.dialog.message.MessageDialogState
import br.com.android.ui.compose.components.styles.DialogTitleTextStyle
import br.com.android.ui.compose.components.styles.ValueTextStyle

/**
 * Um diálogo de mensagem genérico que é controlado por um [MessageDialogState].
 *
 * @param state O estado que define o conteúdo e o comportamento do diálogo.
 * @see [MessageDialogState]
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun BaseMessageDialog(state: MessageDialogState) {
    BaseMessageDialog(
        type = state.dialogType,
        show = state.showDialog,
        onDismissRequest = state.onHideDialog,
        message = state.dialogMessage,
        onConfirm = state.onConfirm,
        onCancel = state.onCancel
    )
}

/**
 * Exibe um diálogo de mensagem genérico (AlertDialog) com base nos parâmetros fornecidos.
 *
 * @param type O tipo do diálogo, que determina o título e os botões exibidos.
 * @param show `true` para exibir o diálogo, `false` para ocultá-lo.
 * @param onDismissRequest Ação a ser executada quando o diálogo for dispensado.
 * @param message A mensagem a ser exibida no corpo do diálogo.
 * @param onConfirm Ação a ser executada quando o botão de confirmação for clicado.
 * @param onCancel Ação a ser executada quando o botão de cancelamento for clicado.
 * @param containerColor A cor de fundo do diálogo.
 * @param textContentColor A cor do texto do diálogo.
 *
 * @see [EnumDialogType]
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun BaseMessageDialog(
    type: EnumDialogType,
    show: Boolean,
    onDismissRequest: () -> Unit,
    message: String,
    onConfirm: () -> Unit = { },
    onCancel: () -> Unit = { },
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainer,
    textContentColor: Color = MaterialTheme.colorScheme.onSurface
) {
    val scrollState = rememberScrollState()

    if (show) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = {
                Text(
                    text = stringResource(type.titleResId),
                    style = DialogTitleTextStyle
                )
            },
            text = {
                Box(modifier = Modifier.verticalScroll(state = scrollState)) {
                    Text(
                        text = message,
                        style = ValueTextStyle
                    )
                }
            },
            confirmButton = {
                when (type) {
                    EnumDialogType.ERROR, EnumDialogType.INFORMATION -> {
                        DefaultDialogTextButton(
                            labelResId = R.string.label_ok,
                            onClick = {
                                onDismissRequest()
                                onConfirm()
                            }
                        )
                    }

                    EnumDialogType.CONFIRMATION -> {
                        DefaultDialogTextButton(
                            labelResId = R.string.label_confirm,
                            onClick = {
                                onDismissRequest()
                                onConfirm()
                            }
                        )
                    }
                }
            },
            dismissButton = {
                when (type) {
                    EnumDialogType.CONFIRMATION -> {
                        DefaultDialogTextButton(
                            labelResId = R.string.label_cancel,
                            onClick = {
                                onDismissRequest()
                                onCancel()
                            }
                        )
                    }

                    EnumDialogType.ERROR, EnumDialogType.INFORMATION -> {}
                }
            },
            containerColor = containerColor,
            textContentColor = textContentColor,
        )
    }
}