package br.com.android.ui.compose.components.dialog.message

import br.com.android.ui.compose.components.dialog.message.enums.EnumDialogType
import br.com.android.ui.compose.components.dialog.message.interfaces.IShowMessageDialogCallback

/**
 * Representa o estado de um diálogo de mensagem em uma UI do Jetpack Compose.
 *
 * Esta data class contém todas as informações necessárias para controlar a exibição,
 * o conteúdo e as ações de um diálogo, seguindo o padrão de State Hoisting.
 * Uma instância deste estado normalmente é mantida em um ViewModel e observada pela UI.
 *
 * @property dialogType O tipo do diálogo a ser exibido.
 * @property dialogMessage A mensagem principal a ser exibida no corpo do diálogo.
 * @property showDialog Controla a visibilidade do diálogo. `true` para exibir, `false` para ocultar.
 * @property onShowDialog Callback utilizado para exibir o diálogo.
 * @property onHideDialog Lambda a ser invocada quando o diálogo é dispensado. A implementação deve,
 * no mínimo, definir `showDialog` como `false`.
 * @property onConfirm Lambda a ser invocada quando o usuário clica no botão de positivo.
 * @property onCancel Lambda a ser invocada quando o usuário clica no botão de negativo.
 */
data class MessageDialogState(
    val dialogType: EnumDialogType = EnumDialogType.ERROR,
    val dialogMessage: String = "",
    val showDialog: Boolean = false,
    val onShowDialog: IShowMessageDialogCallback? = null,
    val onHideDialog: () -> Unit = { },
    val onConfirm: () -> Unit = { },
    val onCancel: () -> Unit = { }
)