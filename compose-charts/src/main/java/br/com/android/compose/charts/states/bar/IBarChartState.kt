package br.com.android.compose.charts.states.bar

import br.com.android.compose.charts.entries.bar.BarChartEntry
import br.com.android.compose.charts.states.legend.ChartLegendState

/**
 * Define um contrato para o estado de um gráfico de barras.
 *
 * Esta interface padroniza o acesso aos dados e ao estado da legenda,
 * sendo aplicável tanto a gráficos de barras simples quanto agrupados.
 *
 * @property entries A lista de entradas de dados ([BarChartEntry]) para o gráfico.
 * @property legendState O estado da legenda do gráfico, ou `null` se não houver legenda.
 *
 * @author Nikolas Luiz Schmitt
 */
interface IBarChartState {
    val entries: List<BarChartEntry>
    val legendState: ChartLegendState?
}