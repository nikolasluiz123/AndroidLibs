package br.com.android.ui.compose.components.fields.dropdown.state

import br.com.android.ui.compose.components.fields.dropdown.MenuItem
import br.com.android.ui.compose.components.fields.text.state.ITextField

/**
 * Representa o estado de um campo de texto com menu suspenso ([br.com.android.ui.compose.components.fields.dropdown.DefaultExposedDropdownMenu]).
 *
 * @param T O tipo de valor dos itens do menu.
 * @property dataList A lista completa de itens do menu.
 * @property dataListFiltered A lista de itens do menu após a aplicação de filtros.
 * @property expanded `true` se o menu estiver expandido, `false` caso contrário.
 * @property onDropDownExpandedChange Callback para quando o estado de expansão do menu for alterado.
 * @property onDropDownDismissRequest Callback para quando o menu for dispensado.
 * @property onDataListItemClick Callback para quando um item do menu for clicado.
 * @property value O valor atual do campo de texto.
 * @property onChange Callback para quando o valor do campo de texto for alterado.
 * @property errorMessage A mensagem de erro a ser exibida.
 *
 * @author Nikolas Luiz Schmitt
 */
data class DropDownTextField<T>(
    val dataList: List<MenuItem<T?>> = emptyList(),
    val dataListFiltered: List<MenuItem<T?>> = emptyList(),
    val expanded: Boolean = false,
    val onDropDownExpandedChange: (Boolean) -> Unit = { },
    val onDropDownDismissRequest: () -> Unit = { },
    val onDataListItemClick: (MenuItem<T?>) -> Unit = { },
    override val value: String = "",
    override val onChange: (String) -> Unit = { },
    override val errorMessage: String = ""
): ITextField