package br.com.android.ui.compose.components.list.grouped

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import br.com.android.ui.compose.components.list.EmptyState
import br.com.android.ui.compose.components.styles.LabelTextStyle

/**
 * Um [LazyColumn] que exibe uma lista de itens agrupados.
 *
 * @param T O tipo do item individual.
 * @param GROUP O tipo do grupo, que deve implementar [IBasicGroup].
 * @param groups A lista de grupos a serem exibidos.
 * @param groupLayout O `Composable` para renderizar o cabeçalho de cada grupo.
 * @param itemLayout O `Composable` para renderizar cada item dentro de um grupo.
 * @param emptyMessageResId O ID do recurso de string para a mensagem exibida quando a lista está vazia.
 * @param emptyMessageTextStyle O estilo do texto para a mensagem de lista vazia.
 * @param emptyMessageColor A cor do texto para a mensagem de lista vazia.
 * @param modifier O [Modifier] a ser aplicado ao [LazyColumn].
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun <T, GROUP : IBasicGroup<T>> LazyGroupedVerticalList(
    groups: List<GROUP>,
    groupLayout: @Composable (GROUP) -> Unit,
    itemLayout: @Composable (T) -> Unit,
    emptyMessageResId: Int,
    emptyMessageTextStyle: TextStyle = LabelTextStyle,
    emptyMessageColor: Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    if (groups.isNotEmpty()) {
        LazyColumn(
            modifier = modifier,
            content = {
                groups.forEach { group ->
                    item {
                        groupLayout(group)
                    }

                    items(group.items.size) { index ->
                        itemLayout(group.items[index])
                    }
                }
            }
        )
    } else {
        EmptyState(
            emptyMessage = stringResource(id = emptyMessageResId),
            color = emptyMessageColor,
            textStyle = emptyMessageTextStyle
        )
    }
}