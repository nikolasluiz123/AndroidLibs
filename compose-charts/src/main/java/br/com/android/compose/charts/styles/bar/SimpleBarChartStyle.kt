package br.com.android.compose.charts.styles.bar

import br.com.android.compose.charts.styles.ChartContainerStyle
import br.com.android.compose.charts.styles.legend.ChartLegendStyle

/**
 * Define o estilo para um [br.com.android.compose.charts.composables.bar.simple.SimpleBarChart].
 *
 * @property defaultBarStyle O [BarStyle] padrão a ser usado para todas as barras, a menos que
 * um estilo específico seja fornecido em [barStyles].
 * @property backgroundStyle O estilo do container e eixos do gráfico.
 * @property barStyles Uma lista opcional de [BarStyle] para aplicar estilos individuais a cada
 * barra sequencialmente. Se a lista for menor que o número de barras, o [defaultBarStyle]
 * será usado para as barras restantes.
 * @property legendStyle O estilo da legenda do gráfico.
 *
 * @author Nikolas Luiz Schmitt
 */
data class SimpleBarChartStyle(
    val defaultBarStyle: BarStyle?,
    val backgroundStyle: ChartContainerStyle = ChartContainerStyle(),
    val barStyles: List<BarStyle> = emptyList(),
    val legendStyle: ChartLegendStyle = ChartLegendStyle()
)