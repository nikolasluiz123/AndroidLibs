package br.com.android.ui.compose.components.fields.text.dialog

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import br.com.android.ui.compose.components.buttons.icons.IconButtonSearch
import br.com.android.ui.compose.components.dialog.list.BaseListDialog
import br.com.android.ui.compose.components.dialog.list.interfaces.ISimpleDialogListItem
import br.com.android.ui.compose.components.fields.text.OutlinedTextFieldValidation
import br.com.android.ui.compose.components.fields.text.dialog.state.DialogListTextField

@Composable
fun <T: ISimpleDialogListItem> ListDialogOutlinedTextFieldValidation(
    field: DialogListTextField<T>,
    fieldLabel: String,
    simpleFilterPlaceholderResId: Int,
    emptyMessage: Int,
    itemLayout: @Composable (T) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
) {
    OutlinedTextFieldValidation(
        field = field,
        label = fieldLabel,
        modifier = modifier,
        keyboardOptions = keyboardOptions,
        trailingIcon = {
            IconButtonSearch(
                onClick = field.dialogListState.onShow,
                iconColor = MaterialTheme.colorScheme.secondary
            )
        }
    )

    if (field.dialogListState.show) {
        BaseListDialog(
            state = field.dialogListState,
            simpleFilterPlaceholderResId = simpleFilterPlaceholderResId,
            emptyMessage = emptyMessage,
            itemLayout = itemLayout
        )
    }
}