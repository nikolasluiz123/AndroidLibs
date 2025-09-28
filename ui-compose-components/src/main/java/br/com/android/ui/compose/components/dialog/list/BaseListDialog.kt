package br.com.android.ui.compose.components.dialog.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import br.com.core.android.utils.interfaces.ISimpleListItem
import br.com.android.ui.compose.components.list.EmptyState
import br.com.android.ui.compose.components.simplefilter.SimpleFilter
import br.com.android.ui.compose.components.styles.DialogTitleTextStyle

/**
 * Um diálogo que exibe uma lista de itens filtráveis.
 * Esta é uma sobrecarga que usa um [DialogListState] para gerenciar o estado do diálogo.
 *
 * @param T O tipo de item na lista, que deve implementar [ISimpleListItem].
 * @param state O estado que controla o diálogo, incluindo a lista de itens e a visibilidade.
 * @param simpleFilterPlaceholderResId O ID do recurso de string para o placeholder do campo de filtro.
 * @param emptyMessage O ID do recurso de string para a mensagem exibida quando a lista está vazia.
 * @param itemLayout O [Composable] que define como cada item da lista é renderizado.
 *
 * @see [DialogListState]
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun <T : ISimpleListItem> BaseListDialog(
    state: DialogListState<T>,
    simpleFilterPlaceholderResId: Int,
    emptyMessage: Int,
    itemLayout: @Composable (T) -> Unit
) {
    BaseListDialog(
        dialogTitle = state.dialogTitle,
        items = state.dataList,
        onDismissRequest = state.onHide,
        onSimpleFilterChange = state.onSimpleFilterChange,
        simpleFilterPlaceholderResId = simpleFilterPlaceholderResId,
        emptyMessage = emptyMessage,
        itemLayout = itemLayout
    )
}

/**
 * Um diálogo que exibe uma lista de itens filtráveis.
 *
 * @param T O tipo de item na lista, que deve implementar [ISimpleListItem].
 * @param dialogTitle O título do diálogo.
 * @param items A lista de itens a serem exibidos.
 * @param simpleFilterPlaceholderResId O ID do recurso de string para o placeholder do campo de filtro.
 * @param emptyMessage O ID do recurso de string para a mensagem exibida quando a lista está vazia.
 * @param itemLayout O [Composable] que define como cada item da lista é renderizado.
 * @param onDismissRequest Ação a ser executada quando o diálogo for dispensado.
 * @param onSimpleFilterChange Ação a ser executada quando o texto do filtro for alterado.
 * @param dialogShape A forma do diálogo.
 * @param dialogContainerColor A cor do contêiner do diálogo.
 * @param dialogTitleTextStyle O estilo do texto do título do diálogo.
 * @param dialogTitleTextColor A cor do texto do título do diálogo.
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun <T : ISimpleListItem> BaseListDialog(
    dialogTitle: String,
    items: List<T>,
    simpleFilterPlaceholderResId: Int,
    emptyMessage: Int,
    itemLayout: @Composable (T) -> Unit,
    onDismissRequest: () -> Unit = { },
    onSimpleFilterChange: (String) -> Unit  = { },
    dialogShape: Shape = MaterialTheme.shapes.medium,
    dialogContainerColor: Color = MaterialTheme.colorScheme.surfaceContainer,
    dialogTitleTextStyle: TextStyle = DialogTitleTextStyle,
    dialogTitleTextColor: Color = MaterialTheme.colorScheme.onSurface
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
                    color = dialogTitleTextColor,
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
                    BaseListDialog(
                        items,
                        emptyMessage,
                        itemLayout
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                BaseListDialog(
                    items,
                    emptyMessage,
                    itemLayout
                )
            }
        }
    }
}

@Composable
private fun <T : ISimpleListItem> BaseListDialog(
    items: List<T>,
    emptyMessage: Int,
    itemLayout: @Composable (T) -> Unit,
    emptyStateTextColor: Color = MaterialTheme.colorScheme.onSurface
) {
    if (items.isEmpty()) {
        EmptyState(
            emptyMessage = stringResource(id = emptyMessage),
            color = emptyStateTextColor
        )
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = items) { item ->
                itemLayout(item)
            }
        }
    }

}