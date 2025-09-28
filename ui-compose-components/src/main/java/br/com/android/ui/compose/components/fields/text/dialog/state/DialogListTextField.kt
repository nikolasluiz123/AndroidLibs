package br.com.android.ui.compose.components.fields.text.dialog.state

import br.com.android.ui.compose.components.dialog.list.DialogListState
import br.com.core.android.utils.interfaces.ISimpleListItem
import br.com.android.ui.compose.components.fields.text.state.ITextField

data class DialogListTextField<T: ISimpleListItem>(
    val dialogListState: DialogListState<T> = DialogListState(),
    override val value: String = "",
    override val onChange: (String) -> Unit = { },
    override val errorMessage: String = ""
): ITextField