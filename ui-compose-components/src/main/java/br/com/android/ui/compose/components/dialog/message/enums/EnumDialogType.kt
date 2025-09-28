package br.com.android.ui.compose.components.dialog.message.enums

import br.com.android.ui.compose.components.R

/**
 * Enumerador para representar os tipos de dialogs
 */
enum class EnumDialogType(val titleResId: Int) {
    ERROR(R.string.error_dialog_title),
    CONFIRMATION(R.string.warning_dialog_title),
    INFORMATION(R.string.information_dialog_title)
}