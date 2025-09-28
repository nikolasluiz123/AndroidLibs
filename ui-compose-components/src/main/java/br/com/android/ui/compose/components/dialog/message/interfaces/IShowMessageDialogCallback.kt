package br.com.android.ui.compose.components.dialog.message.interfaces

import br.com.android.ui.compose.components.dialog.message.enums.EnumDialogType

fun interface IShowMessageDialogCallback {
    fun onShow(type: EnumDialogType, message: String, onConfirm: () -> Unit, onCancel: () -> Unit)
}