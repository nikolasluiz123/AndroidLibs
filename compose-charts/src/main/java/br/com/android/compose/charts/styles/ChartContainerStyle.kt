package br.com.android.compose.charts.styles

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.android.compose.charts.styles.enums.GridLineStyle
import br.com.android.compose.charts.styles.text.ChartTextStyle

/**
 * Define o estilo para a área de fundo e os eixos de um gráfico.
 *
 * @property showXAxisLabels Controla a visibilidade dos rótulos no eixo X.
 * @property xAxisLabelStyle O estilo de texto para os rótulos do eixo X.
 * @property showYAxisLabels Controla a visibilidade dos rótulos no eixo Y.
 * @property showYAxisLines Controla a visibilidade das linhas de grade do eixo Y.
 * @property yAxisSteps O número de intervalos a serem exibidos no eixo Y.
 * @property yAxisLabelStyle O estilo de texto para os rótulos do eixo Y.
 * @property gridLineColor A cor das linhas de grade.
 * @property gridLineWidth A espessura das linhas de grade.
 * @property gridLineStyle O estilo das linhas de grade (sólida, tracejada, pontilhada).
 * @property enableHorizontalScroll Habilita o scroll horizontal para gráficos com muitas entradas.
 * @property scrollableBarWidth A largura de cada "slot" de barra quando o scroll está habilitado.
 *
 * @author Nikolas Luiz Schmitt
 */
data class ChartContainerStyle(
    val showXAxisLabels: Boolean = true,
    val xAxisLabelStyle: ChartTextStyle = ChartTextStyle(),

    val showYAxisLabels: Boolean = true,
    val showYAxisLines: Boolean = true,
    val yAxisSteps: Int = 5,
    val yAxisLabelStyle: ChartTextStyle = ChartTextStyle(),

    val gridLineColor: Color = Color.LightGray,
    val gridLineWidth: Dp = 1.dp,
    val gridLineStyle: GridLineStyle = GridLineStyle.DOTTED,

    val enableHorizontalScroll: Boolean = false,
    val scrollableBarWidth: Dp = 96.dp
)