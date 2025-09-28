package br.com.android.compose.charts.states.bar

import br.com.android.compose.charts.entries.bar.GroupedBarEntry
import br.com.android.compose.charts.states.legend.ChartLegendState

/**
 * Representa o estado de um [br.com.android.compose.charts.composables.bar.grouped.GroupedBarChart].
 *
 * @property entries A lista de entradas [GroupedBarEntry] a serem exibidas no gráfico.
 * @property legendState O estado da legenda, ou `null` para desabilitá-la.
 *
 * @author Nikolas Luiz Schmitt
 */
data class GroupedBarChartState(
    override val entries: List<GroupedBarEntry> = emptyList(),
    override val legendState: ChartLegendState? = null
) : IBarChartState