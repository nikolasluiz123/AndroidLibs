package br.com.android.compose.charts.states.line

import br.com.android.compose.charts.entries.line.LineChartPointEntry
import br.com.android.compose.charts.states.legend.ChartLegendState

/**
 * Representa o estado de um [br.com.android.compose.charts.composables.line.LineChart].
 *
 * @property entries A lista de pontos de dados [LineChartPointEntry] a serem plotados.
 * @property legendState O estado da legenda, ou `null` para desabilitá-la.
 *
 * @author Nikolas Luiz Schmitt
 */
data class LineChartState(
    val entries: List<LineChartPointEntry> = emptyList(),
    val legendState: ChartLegendState? = null
)