package br.com.android.ui.compose.components.dialog.list.paged

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.android.ui.compose.components.dialog.list.interfaces.ISimpleDialogListItem
import br.com.android.ui.compose.components.dialog.list.paged.PagedDialogListState
import br.com.android.ui.compose.components.list.PagedLazyVerticalList
import br.com.android.ui.compose.components.simplefilter.SimpleFilter
import br.com.android.ui.compose.components.styles.DialogTitleTextStyle

/**
 * Um diálogo que exibe uma lista paginada de itens filtráveis, usando um [PagedDialogListState].
 *
 * @param T O tipo de item na lista, que deve implementar [ISimpleDialogListItem].
 * @param state O estado que gerencia o diálogo.
 * @param simpleFilterPlaceholderResId O ID do recurso de string para o placeholder do filtro.
 * @param emptyMessage O ID do recurso de string para a mensagem de lista vazia.
 * @param itemLayout O [Composable] para renderizar cada item da lista.
 *
 * @see [PagedDialogListState]
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun <T : ISimpleDialogListItem> BasePagedListDialog(
    state: PagedDialogListState<T>,
    simpleFilterPlaceholderResId: Int,
    emptyMessage: Int,
    itemLayout: @Composable (T) -> Unit
) {
    BasePagedListDialog(
        dialogTitle = state.dialogTitle,
        pagingItems = state.dataList.collectAsLazyPagingItems(),
        onDismissRequest = state.onHide,
        onSimpleFilterChange = state.onSimpleFilterChange,
        simpleFilterPlaceholderResId = simpleFilterPlaceholderResId,
        emptyMessage = emptyMessage,
        itemLayout = itemLayout
    )
}

/**
 * Um diálogo que exibe uma lista paginada de itens filtráveis.
 *
 * @param T O tipo de item na lista, que deve implementar [ISimpleDialogListItem].
 * @param dialogTitle O título do diálogo.
 * @param pagingItems Os itens paginados a serem exibidos.
 * @param simpleFilterPlaceholderResId O ID do recurso de string para o placeholder do filtro.
 * @param emptyMessage O ID do recurso de string para a mensagem de lista vazia.
 * @param itemLayout O [Composable] para renderizar cada item da lista.
 * @param onDismissRequest Callback para quando o diálogo for dispensado.
 * @param onSimpleFilterChange Callback para quando o texto do filtro mudar.
 * @param dialogShape A forma do diálogo.
 * @param dialogContainerColor A cor do contêiner do diálogo.
 * @param dialogTitleTextStyle O estilo do texto do título.
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun <T : ISimpleDialogListItem> BasePagedListDialog(
    dialogTitle: String,
    pagingItems: LazyPagingItems<T>,
    simpleFilterPlaceholderResId: Int,
    emptyMessage: Int,
    itemLayout: @Composable (T) -> Unit,
    onDismissRequest: () -> Unit = { },
    onSimpleFilterChange: (String) -> Unit  = { },
    dialogShape: Shape = MaterialTheme.shapes.medium,
    dialogContainerColor: Color = MaterialTheme.colorScheme.surfaceContainer,
    dialogTitleTextStyle: TextStyle = DialogTitleTextStyle
) {
    var filterText by remember { mutableStateOf("") }
    var isFilterExpanded by remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            shape = dialogShape,
            color = dialogContainerColor,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = dialogTitle,
                    style = dialogTitleTextStyle,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                        .align(Alignment.CenterHorizontally)
                )

                SimpleFilter(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    placeholderResId = simpleFilterPlaceholderResId,
                    quickFilter = filterText,
                    onSimpleFilterChange = {
                        filterText = it
                        onSimpleFilterChange(it)
                    },
                    expanded = isFilterExpanded,
                    onExpandedChange = { isFilterExpanded = it }
                ) {
                    PagedListDialog(pagingItems, emptyMessage, itemLayout)
                }

                Spacer(modifier = Modifier.height(8.dp))

                PagedListDialog(pagingItems, emptyMessage, itemLayout)
            }
        }
    }
}

@Composable
private fun <T : ISimpleDialogListItem> PagedListDialog(
    pagingItems: LazyPagingItems<T>,
    emptyMessage: Int,
    itemLayout: @Composable (T) -> Unit,
    emptyStateTextColor: Color = MaterialTheme.colorScheme.onSurface
) {
    PagedLazyVerticalList(
        modifier = Modifier.fillMaxWidth(),
        pagingItems = pagingItems,
        itemLayout = itemLayout,
        emptyMessageResId = emptyMessage,
        emptyStateTextColor = emptyStateTextColor
    )
}