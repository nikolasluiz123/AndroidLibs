package br.com.android.ui.compose.components.fields.text.dialog.paged.state

import br.com.android.ui.compose.components.dialog.list.interfaces.ISimpleDialogListItem
import br.com.android.ui.compose.components.dialog.list.paged.PagedDialogListState
import br.com.android.ui.compose.components.fields.text.state.ITextField

data class PagedDialogListTextField<T: ISimpleDialogListItem>(
    val dialogListState: PagedDialogListState<T> = PagedDialogListState(),
    override val value: String = "",
    override val onChange: (String) -> Unit = { },
    override val errorMessage: String = ""
): ITextField