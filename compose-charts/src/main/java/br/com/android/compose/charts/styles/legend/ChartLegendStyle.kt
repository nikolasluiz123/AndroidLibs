package br.com.android.compose.charts.styles.legend

import androidx.compose.ui.graphics.Color
import br.com.android.compose.charts.styles.text.ChartTextStyle

/**
 * Define o estilo para a legenda de um gráfico.
 *
 * @property textStyle O estilo de texto para os rótulos da legenda.
 * @property colors Uma lista de cores a serem usadas para os indicadores (círculos)
 * de cada item da legenda. As cores são aplicadas sequencialmente.
 *
 * @author Nikolas Luiz Schmitt
 */
data class ChartLegendStyle(
    val textStyle: ChartTextStyle = ChartTextStyle(),
    val colors: List<Color> = emptyList(),
)