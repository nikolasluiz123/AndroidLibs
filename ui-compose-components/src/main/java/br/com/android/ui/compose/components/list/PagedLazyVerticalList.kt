package br.com.android.ui.compose.components.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import br.com.android.ui.compose.components.R
import kotlin.let

/**
 * Exibe uma lista de rolagem vertical de itens usando dados paginados de [LazyPagingItems].
 * Lida com os estados de carregamento, erro e vazio.
 *
 * @param T O tipo de dados dos itens na lista.
 * @param pagingItems Os itens paginados a serem exibidos.
 * @param modifier O [Modifier] a ser aplicado ao [LazyColumn].
 * @param emptyMessageResId O ID do recurso de string para a mensagem a ser exibida quando a lista estiver vazia.
 * @param emptyStateTextColor A cor do texto da mensagem de estado vazio.
 * @param verticalArrangementSpace O espaçamento vertical entre os itens.
 * @param contentPadding O preenchimento ao redor do conteúdo da lista.
 * @param loadingColor A cor do indicador de progresso.
 * @param itemLayout O [Composable] que define a aparência de cada item na lista.
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun <T: Any> PagedLazyVerticalList(
    pagingItems: LazyPagingItems<T>,
    modifier: Modifier = Modifier,
    emptyMessageResId: Int? = null,
    emptyStateTextColor: Color = MaterialTheme.colorScheme.onBackground,
    verticalArrangementSpace: Dp = 0.dp,
    contentPadding: Dp = 0.dp,
    loadingColor: Color = MaterialTheme.colorScheme.primary,
    itemLayout: @Composable (T) -> Unit
) {
    if (pagingItems.itemCount > 0) {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(verticalArrangementSpace),
            contentPadding = PaddingValues(contentPadding)
        ) {
            when (pagingItems.loadState.refresh) {
                is LoadState.Loading -> {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            color = loadingColor
                        )
                    }
                }

                is LoadState.Error -> {
                    item {
                        Text(
                            text = stringResource(R.string.paged_list_error_load_items),
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                is LoadState.NotLoading -> {
                    items(
                        count = pagingItems.itemCount,
                        key = pagingItems.itemKey(),
                        contentType = pagingItems.itemContentType()
                    ) { index ->
                        pagingItems[index]?.let { item ->
                            itemLayout(item)
                        }
                    }
                }
            }

            when (pagingItems.loadState.append) {
                is LoadState.Loading -> {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            color = loadingColor
                        )
                    }
                }

                is LoadState.Error -> {
                    item {
                        Text(
                            text = stringResource(R.string.paged_list_error_load_new_items),
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                else -> {}
            }
        }
    } else {
        emptyMessageResId?.let { emptyMessage ->
            EmptyState(
                emptyMessage = stringResource(id = emptyMessage),
                color = emptyStateTextColor
            )
        }
    }
}