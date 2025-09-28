package br.com.android.ui.compose.components.list.grouped.expandable

/**
 * Define a estrutura para um grupo expansível em uma lista.
 *
 * @param T O tipo dos itens contidos neste grupo.
 * @property id Um identificador único para o grupo.
 * @property label O ID do recurso de string para o rótulo do grupo.
 * @property value Um valor de texto a ser exibido no cabeçalho do grupo.
 * @property isExpanded `true` se o grupo estiver expandido, `false` caso contrário.
 * @property items A lista de itens que pertencem a este grupo.
 *
 * @author Nikolas Luiz Schmitt
 */
interface IBasicExpandableGroup<T> {
    val id: String
    val label: Int
    val value: String
    var isExpanded: Boolean
    val items: List<T>
}