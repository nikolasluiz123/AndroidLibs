package br.com.android.ui.compose.components.dialog.list

import br.com.core.android.utils.interfaces.ISimpleListItem

/**
 * Representa o estado de um [BaseListDialog].
 *
 * @param T O tipo de item na lista, que deve implementar [ISimpleListItem].
 * @property dialogTitle O título a ser exibido no diálogo.
 * @property dataList A lista de itens a serem exibidos.
 * @property show `true` se o diálogo deve ser exibido, `false` caso contrário.
 * @property onShow Callback para exibir o diálogo.
 * @property onHide Callback para ocultar o diálogo.
 * @property onDataListItemClick Callback para quando um item da lista é clicado.
 * @property onSimpleFilterChange Callback para quando o texto do filtro é alterado.
 *
 * @see [BaseListDialog]
 * @author Nikolas Luiz Schmitt
 */
data class DialogListState<T: ISimpleListItem>(
    val dialogTitle: String = "",
    val dataList: List<T> = emptyList(),
    val show: Boolean = false,
    val onShow: () -> Unit = { },
    val onHide: () -> Unit = { },
    val onDataListItemClick: (T) -> Unit = { },
    val onSimpleFilterChange: (String) -> Unit = { }
)