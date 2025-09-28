package br.com.android.ui.compose.components.fields.text.dialog.state

import br.com.android.ui.compose.components.dialog.list.DialogListState
import br.com.android.ui.compose.components.dialog.list.interfaces.ISimpleDialogListItem
import br.com.android.ui.compose.components.fields.text.state.ITextField

data class DialogListTextField<T: ISimpleDialogListItem>(
    val dialogListState: DialogListState<T> = DialogListState(),
    override val value: String = "",
    override val onChange: (String) -> Unit = { },
    override val errorMessage: String = ""
): ITextField