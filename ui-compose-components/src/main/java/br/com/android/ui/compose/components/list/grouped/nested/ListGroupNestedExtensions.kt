package br.com.android.ui.compose.components.list.grouped.nested

import br.com.android.ui.compose.components.list.grouped.IBasicGroup

/**
 * Transforma uma lista hierárquica de grupos em uma lista plana de [ListNode]s,
 * preservando a profundidade de cada nó.
 *
 * @param T O tipo dos itens nos grupos.
 * @param depth A profundidade inicial da recursão.
 * @return Uma lista plana de [ListNode]s.
 *
 * @author Nikolas Luiz Schmitt
 */
fun <T> List<IBasicGroup<T>>.flattenGroups(depth: Int = 0): List<ListNode> {
    return flatMap { group ->
        val header = ListNode.GroupNode(group, depth)
        val children = group.items.flatMap { item ->
            when (item) {
                is IBasicGroup<*> -> {
                    item.getAsGroupList().flattenGroups(depth + 1)
                }
                else -> {
                    listOf(ListNode.ItemNode(item, depth + 1))
                }
            }
        }
        listOf(header) + children
    }
}

private fun <T> IBasicGroup<T>.getAsGroupList(): List<IBasicGroup<T>> = listOf(this)