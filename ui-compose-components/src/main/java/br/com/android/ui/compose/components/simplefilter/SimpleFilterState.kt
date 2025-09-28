package br.com.android.ui.compose.components.simplefilter

/**
 * Representa o estado para o componente [SimpleFilter].
 *
 * @property quickFilter O texto atual inserido no campo de filtro.
 * @property simpleFilterExpanded Controla o estado de expansão (ativo/inativo) do filtro.
 * @property onSimpleFilterChange Callback para notificar sobre mudanças no texto do filtro.
 * @property onExpandedChange Callback para notificar sobre mudanças no estado de expansão.
 *
 * @author Nikolas Luiz Schmitt
 */
data class SimpleFilterState(
    val quickFilter: String = "",
    val simpleFilterExpanded: Boolean = false,
    val onSimpleFilterChange: (String) -> Unit = { },
    val onExpandedChange: (Boolean) -> Unit = { }
)