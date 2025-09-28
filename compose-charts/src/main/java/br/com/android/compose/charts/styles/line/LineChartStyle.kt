package br.com.android.compose.charts.styles.line

import br.com.android.compose.charts.styles.ChartContainerStyle
import br.com.android.compose.charts.styles.legend.ChartLegendStyle

/**
 * Define o estilo para um [br.com.android.compose.charts.composables.line.LineChart].
 *
 * @property lineStyles Uma lista de [LineStyle], onde cada estilo corresponde a uma linha
 * no gráfico.
 * @property backgroundStyle O estilo do container e eixos do gráfico.
 * @property legendStyle O estilo da legenda do gráfico.
 *
 * @author Nikolas Luiz Schmitt
 */
data class LineChartStyle(
    val lineStyles: List<LineStyle>,
    val backgroundStyle: ChartContainerStyle = ChartContainerStyle(),
    val legendStyle: ChartLegendStyle = ChartLegendStyle()
)