package br.com.android.compose.charts.styles.bar

import br.com.android.compose.charts.styles.ChartContainerStyle
import br.com.android.compose.charts.styles.legend.ChartLegendStyle

/**
 * Define o estilo para um [br.com.android.compose.charts.composables.bar.grouped.GroupedBarChart].
 *
 * @property defaultBarStyles Uma lista de [BarStyle] a ser aplicada sequencialmente a cada barra
 * dentro de um grupo. Se um grupo tiver mais barras do que estilos definidos, o primeiro estilo
 * da lista será reutilizado.
 * @property backgroundStyle O estilo do container e eixos do gráfico.
 * @property legendStyle O estilo da legenda do gráfico.
 *
 * @author Nikolas Luiz Schmitt
 */
data class GroupedBarChartStyle(
    val defaultBarStyles: List<BarStyle>,
    val backgroundStyle: ChartContainerStyle = ChartContainerStyle(),
    val legendStyle: ChartLegendStyle = ChartLegendStyle()
)