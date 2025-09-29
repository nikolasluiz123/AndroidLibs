package br.com.android.ui.compose.components.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow

/**
 * Exibe uma lista de rolagem vertical de itens.
 * Se a lista estiver vazia, um estado de "vazio" é exibido.
 *
 * @param T O tipo de dados dos itens na lista.
 * @param items A lista de itens a serem exibidos.
 * @param emptyMessageResId O ID do recurso de string para a mensagem a ser exibida quando a lista estiver vazia.
 * @param modifier O [Modifier] a ser aplicado ao [LazyColumn].
 * @param verticalArrangementSpace O espaçamento vertical entre os itens.
 * @param contentPadding O preenchimento ao redor do conteúdo da lista.
 * @param reverseLayout `true` para exibir os itens na ordem inversa.
 * @param itemList O [Composable] que define a aparência de cada item na lista.
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun <T> LazyVerticalList(
    items: List<T>,
    emptyMessageResId: Int?,
    modifier: Modifier = Modifier,
    verticalArrangementSpace: Dp = 0.dp,
    contentPadding: Dp = 0.dp,
    reverseLayout: Boolean = false,
    itemList: @Composable (T) -> Unit
) {
    if (items.isNotEmpty()) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(verticalArrangementSpace),
            contentPadding = PaddingValues(contentPadding),
            reverseLayout = reverseLayout
        ) {
            items(items = items) { item ->
                itemList(item)
            }
        }
    } else {
        emptyMessageResId?.let { emptyMessage ->
            EmptyState(
                modifier = modifier,
                emptyMessage = stringResource(id = emptyMessage)
            )
        }
    }
}

/**
 * Exibe uma lista de rolagem vertical de itens a partir de um [Flow].
 * Se a lista estiver vazia, um estado de "vazio" é exibido.
 *
 * @param T O tipo de dados dos itens na lista.
 * @param itemsFlow Um [Flow] que emite a lista de itens a serem exibidos.
 * @param emptyMessageResId O ID do recurso de string para a mensagem a ser exibida quando a lista estiver vazia.
 * @param modifier O [Modifier] a ser aplicado ao [LazyColumn].
 * @param verticalArrangementSpace O espaçamento vertical entre os itens.
 * @param contentPadding O preenchimento ao redor do conteúdo da lista.
 * @param reverseLayout `true` para exibir os itens na ordem inversa.
 * @param itemList O [Composable] que define a aparência de cada item na lista.
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun <T> LazyVerticalList(
    itemsFlow: Flow<List<T>>,
    emptyMessageResId: Int?,
    modifier: Modifier = Modifier,
    verticalArrangementSpace: Dp = 0.dp,
    contentPadding: Dp = 0.dp,
    reverseLayout: Boolean = false,
    itemList: @Composable (T) -> Unit
) {
    val items by itemsFlow.collectAsStateWithLifecycle(emptyList())

    if (items.isNotEmpty()) {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(verticalArrangementSpace),
            contentPadding = PaddingValues(contentPadding),
            reverseLayout = reverseLayout
        ) {
            items(items = items) { item ->
                itemList(item)
            }
        }
    } else {
        emptyMessageResId?.let { emptyMessage ->
            EmptyState(
                modifier = modifier,
                emptyMessage = stringResource(id = emptyMessage)
            )
        }
    }
}