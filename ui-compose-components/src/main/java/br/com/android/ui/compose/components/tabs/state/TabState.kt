package br.com.android.ui.compose.components.tabs.state

import kotlin.collections.first

/**
 * Gerencia o estado de um conjunto de abas (tabs), como as utilizadas em [BaseTabRow].
 *
 * @property tabs A lista mutável de objetos [Tab] que compõem o conjunto.
 * @property onSelectTab A função de callback invocada quando uma nova aba é selecionada.
 *
 * @author Nikolas Luiz Schmitt
 */
data class TabState(
    val tabs: MutableList<Tab> = mutableListOf(),
    val onSelectTab: (Tab) -> Unit = { },
) {
    /**
     * A aba atualmente selecionada.
     */
    val selectedTab: Tab
        get() = tabs.first { it.selected }

    /**
     * O número total de abas.
     */
    val tabsSize: Int
        get() = tabs.size
}