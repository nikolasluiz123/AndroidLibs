package br.com.android.ui.compose.components.fields.dropdown

/**
 * Representa um item em um menu, como um [androidx.compose.material3.DropdownMenu].
 *
 * @param T O tipo de valor associado a este item de menu.
 * @property label O texto a ser exibido para este item.
 * @property value O valor associado a este item.
 * @property selected `true` se este item estiver selecionado, `false` caso contrário.
 *
 * @author Nikolas Luiz Schmitt
 */
data class MenuItem<T>(
    val label: String,
    val value: T,
    var selected: Boolean = false
)