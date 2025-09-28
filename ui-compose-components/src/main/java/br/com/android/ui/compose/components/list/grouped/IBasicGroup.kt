package br.com.android.ui.compose.components.list.grouped

/**
 * Define a estrutura básica para um grupo de itens em uma lista.
 *
 * @param T O tipo dos itens contidos neste grupo.
 * @property id Um identificador único para o grupo.
 * @property label O rótulo de texto a ser exibido para o grupo.
 * @property items A lista de itens que pertencem a este grupo.
 *
 * @author Nikolas Luiz Schmitt
 */
interface IBasicGroup<T> {
    val id: String
    val label: String
    val items: List<T>
}