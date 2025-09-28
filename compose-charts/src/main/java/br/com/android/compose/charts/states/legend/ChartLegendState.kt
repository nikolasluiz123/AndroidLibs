package br.com.android.compose.charts.states.legend

import br.com.android.compose.charts.entries.legend.LegendEntry

/**
 * Representa o estado da legenda de um gráfico.
 *
 * @property entries A lista de entradas [LegendEntry] que compõem a legenda.
 * @property isEnabled Controla a visibilidade da legenda.
 *
 * @author Nikolas Luiz Schmitt
 */
data class ChartLegendState(
    val entries: List<LegendEntry>,
    val isEnabled: Boolean = true,
)