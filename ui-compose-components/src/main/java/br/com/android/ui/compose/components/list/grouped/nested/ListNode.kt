package br.com.android.ui.compose.components.list.grouped.nested

import br.com.android.ui.compose.components.list.grouped.IBasicGroup

/**
 * Representa um nó em uma lista achatada que pode ser um grupo ou um item.
 *
 * @author Nikolas Luiz Schmitt
 */
sealed class ListNode {
    /**
     * Representa um nó de grupo.
     * @param T O tipo dos itens no grupo.
     * @property group O grupo de dados.
     * @property depth A profundidade do grupo na hierarquia.
     */
    data class GroupNode<T>(
        val group: IBasicGroup<T>,
        val depth: Int
    ) : ListNode()

    /**
     * Representa um nó de item.
     * @param T O tipo do item.
     * @property item O item de dados.
     * @property depth A profundidade do item na hierarquia.
     */
    data class ItemNode<T>(
        val item: T,
        val depth: Int
    ) : ListNode()
}