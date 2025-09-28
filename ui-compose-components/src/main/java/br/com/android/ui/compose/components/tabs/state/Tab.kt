package br.com.android.ui.compose.components.tabs.state

import br.com.android.ui.compose.components.tabs.state.interfaces.IEnumTab

/**
 * Representa o estado e as propriedades de uma única aba (Tab) dentro de componentes como
 * [BaseTabRow] e [BaseHorizontalPager].
 *
 * @property enum A instância do [IEnumTab] que define as propriedades intrínsecas da aba, como índice e rótulo.
 * @property selected Indica se a aba está atualmente selecionada.
 * @property enabled Controla se a aba pode ser interagida pelo usuário.
 *
 * @see [IEnumTab]
 * @author Nikolas Luiz Schmitt
 */
data class Tab(
    val enum: IEnumTab,
    var selected: Boolean,
    val enabled: Boolean
)