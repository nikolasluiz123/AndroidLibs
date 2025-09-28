package br.com.android.ui.compose.components.fields.text.dialog.paged

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import br.com.android.ui.compose.components.buttons.icons.IconButtonSearch
import br.com.core.android.utils.interfaces.ISimpleListItem
import br.com.android.ui.compose.components.dialog.list.paged.BasePagedListDialog
import br.com.android.ui.compose.components.fields.text.OutlinedTextFieldValidation
import br.com.android.ui.compose.components.fields.text.dialog.paged.state.PagedDialogListTextField

@Composable
fun <T: ISimpleListItem> PagedListDialogOutlinedTextFieldValidation(
    field: PagedDialogListTextField<T>,
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
            if (field.dialogListState.showTrailingIcon) {
                IconButtonSearch(
                    onClick = field.dialogListState.onShow,
                    iconColor = MaterialTheme.colorScheme.secondary
                )
            }
        }
    )

    if (field.dialogListState.show) {
        BasePagedListDialog(
            state = field.dialogListState,
            simpleFilterPlaceholderResId = simpleFilterPlaceholderResId,
            emptyMessage = emptyMessage,
            itemLayout = itemLayout
        )
    }
}