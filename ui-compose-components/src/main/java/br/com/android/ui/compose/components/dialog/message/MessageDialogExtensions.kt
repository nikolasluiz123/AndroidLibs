package br.com.android.ui.compose.components.dialog.message

import br.com.android.ui.compose.components.dialog.message.enums.EnumDialogType
import br.com.android.ui.compose.components.dialog.message.interfaces.IShowMessageDialogCallback


fun IShowMessageDialogCallback.showErrorDialog(message: String) {
    this.onShow(
        type = EnumDialogType.ERROR,
        message = message,
        onConfirm = { },
        onCancel = { }
    )
}

fun IShowMessageDialogCallback.showConfirmationDialog(message: String, onConfirm: () -> Unit) {
    this.onShow(
        type = EnumDialogType.CONFIRMATION,
        message = message,
        onConfirm = onConfirm,
        onCancel = { }
    )
}

fun IShowMessageDialogCallback.showInformationDialog(message: String) {
    this.onShow(
        type = EnumDialogType.INFORMATION,
        message = message,
        onConfirm = { },
        onCancel = { }
    )
}