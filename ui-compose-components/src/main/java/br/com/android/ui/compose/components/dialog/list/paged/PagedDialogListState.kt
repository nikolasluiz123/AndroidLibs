package br.com.android.ui.compose.components.dialog.list.paged

import androidx.paging.PagingData
import br.com.android.ui.compose.components.dialog.list.interfaces.ISimpleDialogListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

/**
 * Representa o estado de um [BasePagedListDialog].
 *
 * @param T O tipo de item na lista, que deve implementar [ISimpleDialogListItem].
 * @property dialogTitle O título a ser exibido no diálogo.
 * @property dataList Um [Flow] de [PagingData] contendo os itens a serem exibidos na lista paginada.
 * @property show `true` se o diálogo deve ser exibido, `false` caso contrário.
 * @property onShow Callback para exibir o diálogo.
 * @property onHide Callback para ocultar o diálogo.
 * @property onDataListItemClick Callback para quando um item da lista é clicado.
 * @property onSimpleFilterChange Callback para quando o texto do filtro é alterado.
 * @property showTrailingIcon `true` para exibir o ícone de busca, `false` caso contrário.
 *
 * @see [BasePagedListDialog]
 * @author Nikolas Luiz Schmitt
 */
data class PagedDialogListState<T: ISimpleDialogListItem>(
    val dialogTitle: String = "",
    val dataList: Flow<PagingData<T>> = emptyFlow(),
    val show: Boolean = false,
    val onShow: () -> Unit = { },
    val onHide: () -> Unit = { },
    val onDataListItemClick: (T) -> Unit = { },
    val onSimpleFilterChange: (String) -> Unit = { },
    val showTrailingIcon: Boolean = true,
)