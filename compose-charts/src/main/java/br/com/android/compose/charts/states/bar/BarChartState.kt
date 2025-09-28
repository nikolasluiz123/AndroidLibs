package br.com.android.compose.charts.states.bar

import br.com.android.compose.charts.entries.bar.SimpleBarEntry
import br.com.android.compose.charts.states.legend.ChartLegendState

/**
 * Representa o estado de um [br.com.android.compose.charts.composables.bar.simple.SimpleBarChart].
 *
 * @property entries A lista de entradas [SimpleBarEntry] a serem exibidas no gráfico.
 * @property legendState O estado da legenda, ou `null` para desabilitá-la.
 *
 * @author Nikolas Luiz Schmitt
 */
data class BarChartState(
    override val entries: List<SimpleBarEntry> = emptyList(),
    override val legendState: ChartLegendState? = null
) : IBarChartState