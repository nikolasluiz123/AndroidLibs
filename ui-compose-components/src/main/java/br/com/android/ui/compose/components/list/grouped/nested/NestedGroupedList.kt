package br.com.android.ui.compose.components.list.grouped.nested

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import br.com.android.ui.compose.components.list.EmptyState
import br.com.android.ui.compose.components.styles.LabelTextStyle
import br.com.android.ui.compose.components.list.grouped.IBasicGroup

/**
 * Um [LazyColumn] que exibe uma lista hierárquica (aninhada) de grupos e itens.
 *
 * @param T O tipo dos itens.
 * @param rootGroups A lista de grupos raiz a serem exibidos.
 * @param modifier O [Modifier] a ser aplicado ao [LazyColumn].
 * @param onGroup O `Composable` para renderizar o cabeçalho de um grupo.
 * @param onItem O `Composable` para renderizar um item.
 * @param emptyMessageResId O ID do recurso de string para a mensagem de lista vazia.
 * @param emptyMessageTextStyle O estilo do texto para a mensagem de lista vazia.
 * @param emptyMessageColor A cor do texto para a mensagem de lista vazia.
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun <T: Any> NestedGroupedList(
    rootGroups: List<IBasicGroup<T>>,
    modifier: Modifier = Modifier,
    onGroup: @Composable (group: IBasicGroup<Any>, depth: Int) -> Unit,
    onItem: @Composable (item: Any, depth: Int) -> Unit,
    emptyMessageResId: Int,
    emptyMessageTextStyle: TextStyle = LabelTextStyle,
    emptyMessageColor: Color = MaterialTheme.colorScheme.onBackground
) {
    val nodes = remember(rootGroups) {
        rootGroups.flattenGroups()
    }

    if (nodes.isEmpty()) {
        EmptyState(
            emptyMessage = stringResource(id = emptyMessageResId),
            color = emptyMessageColor,
            textStyle = emptyMessageTextStyle
        )
    } else {
        LazyColumn(modifier) {
            items(
                items = nodes,
                key = {
                    when (it) {
                        is ListNode.GroupNode<*> -> it.group.id
                        is ListNode.ItemNode<*> -> it.item.hashCode()
                    }
                },
                itemContent = { node ->
                    when (node) {
                        is ListNode.GroupNode<*> -> onGroup(node.group as IBasicGroup<Any>, node.depth)
                        is ListNode.ItemNode<*> -> onItem(node.item as Any, node.depth)
                    }
                }
            )
        }
    }
}