package br.com.android.compose.charts.composables.line

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.android.compose.charts.composables.container.LineChartContainer
import br.com.android.compose.charts.states.line.LineChartState
import br.com.android.compose.charts.styles.line.LineChartStyle

/**
 * Um Composable que renderiza um gráfico de linhas.
 *
 * Este gráfico é utilizado para exibir a evolução de uma ou mais séries de dados ao longo
 * de um eixo contínuo (geralmente o eixo X).
 *
 * @param state O [LineChartState] que contém os pontos de dados a serem plotados.
 * @param style O [LineChartStyle] que define a aparência do gráfico, incluindo os estilos
 * para cada linha.
 * @param modifier O [Modifier] a ser aplicado ao componente.
 *
 * @see [LineChartContainer]
 * @see [GroupLines]
 * @see [LineChartState]
 * @see [LineChartStyle]
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun LineChart(
    state: LineChartState,
    style: LineChartStyle,
    modifier: Modifier = Modifier
) {
    require(style.lineStyles.isNotEmpty()) { "LineChartStyle requer pelo menos um LineStyle." }

    val maxValue = (state.entries.maxOfOrNull {
        it.values.maxOrNull() ?: 0f
    } ?: 0f).coerceAtLeast(1f)

    LineChartContainer(
        modifier = modifier,
        state = state,
        backgroundStyle = style.backgroundStyle,
        legendStyle = style.legendStyle,
        maxValue = maxValue
    ) { chartHeight, totalChartWidth, actualSlotWidth ->

        GroupLines(
            state = state,
            style = style,
            maxValue = maxValue,
            chartHeight = chartHeight,
            totalChartWidth = totalChartWidth,
            slotWidth = actualSlotWidth,
            isScrollable = style.backgroundStyle.enableHorizontalScroll
        )
    }
}