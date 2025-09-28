package br.com.android.ui.compose.components.list.grouped.expandable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.android.ui.compose.components.list.EmptyState

/**
 * Um [LazyColumn] que exibe uma lista de grupos expansíveis.
 *
 * @param T O tipo do item individual.
 * @param GROUP O tipo do grupo, que deve implementar [IBasicExpandableGroup].
 * @param groups A lista de grupos a serem exibidos.
 * @param itemLayout O `Composable` para renderizar cada item dentro de um grupo.
 * @param emptyMessageResId O ID do recurso de string para a mensagem exibida quando a lista está vazia.
 * @param modifier O [Modifier] a ser aplicado ao [LazyColumn].
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun <T, GROUP : IBasicExpandableGroup<T>> LazyExpandableGroupedVerticalList(
    groups: List<GROUP>,
    itemLayout: @Composable (T) -> Unit,
    emptyMessageResId: Int,
    modifier: Modifier = Modifier
) {
    val expandedStates = rememberSaveable(
        saver = Saver(
            save = { states -> states.mapValues { it.value } },
            restore = { saved ->
                mutableStateMapOf<GROUP, Boolean>().apply {
                    groups.forEach { group ->
                        this[group] = saved[group] ?: false
                    }
                }
            }
        )
    ) {
        mutableStateMapOf<GROUP, Boolean>().apply {
            groups.forEach { group ->
                this[group] = group.isExpanded
            }
        }
    }

    if (groups.isNotEmpty()) {
        LazyColumn(
            modifier = modifier,
            content = {
                groups.forEach { group ->
                    item {
                        BasicExpandableSection(
                            label = stringResource(id = group.label),
                            value = group.value,
                            isExpanded = expandedStates[group] ?: false,
                            onClick = {
                                val isCurrentlyExpanded = expandedStates[group] ?: false
                                expandedStates[group] = !isCurrentlyExpanded
                            }
                        )
                    }

                    items(group.items.size) { index ->
                        AnimatedVisibility(
                            visible = expandedStates[group] == true,
                            enter = expandVertically(),
                            exit = shrinkVertically()
                        ) {
                            itemLayout(group.items[index])
                        }
                    }
                }
            }
        )
    } else {
        EmptyState(
            modifier = modifier,
            emptyMessage = stringResource(id = emptyMessageResId)
        )
    }
}