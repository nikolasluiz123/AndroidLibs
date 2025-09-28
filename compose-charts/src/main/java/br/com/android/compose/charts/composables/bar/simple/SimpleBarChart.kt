package br.com.android.compose.charts.composables.bar.simple

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.android.compose.charts.composables.container.BarChartContainer
import br.com.android.compose.charts.states.bar.BarChartState
import br.com.android.compose.charts.styles.bar.BarStyle
import br.com.android.compose.charts.styles.bar.SimpleBarChartStyle

/**
 * Um Composable que renderiza um gráfico de barras simples.
 *
 * Este gráfico é ideal para exibir uma única série de dados, onde cada entrada no eixo X
 * corresponde a uma única barra vertical.
 *
 * @param state O [BarChartState] que contém os dados a serem exibidos.
 * @param style O [SimpleBarChartStyle] que define a aparência do gráfico.
 * @param modifier O [Modifier] a ser aplicado ao componente.
 *
 * @see [BarChartContainer]
 * @see [SimpleBar]
 * @see [BarChartState]
 * @see [SimpleBarChartStyle]
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun SimpleBarChart(
    state: BarChartState,
    style: SimpleBarChartStyle,
    modifier: Modifier = Modifier
) {
    val maxValue = (state.entries.maxOfOrNull { it.value } ?: 0f).coerceAtLeast(1f)

    BarChartContainer(
        modifier = modifier,
        state = state,
        backgroundStyle = style.backgroundStyle,
        legendStyle = style.legendStyle,
        maxValue = maxValue
    ) { chartHeight, totalChartWidth, actualSlotWidth ->
        val barWidthFraction = 0.5f
        val bgStyle = style.backgroundStyle

        val rowArrangement = if (bgStyle.enableHorizontalScroll) {
            Arrangement.Start
        } else {
            Arrangement.SpaceEvenly
        }

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = rowArrangement,
            verticalAlignment = Alignment.Bottom
        ) {
            state.entries.forEachIndexed { index, entry ->
                val barStyle = getValidStyle(style, index)
                val slotModifier = if (bgStyle.enableHorizontalScroll) {
                    Modifier.width(actualSlotWidth)
                } else {
                    Modifier.weight(1f, fill = true)
                }

                SimpleBar(
                    entry = entry,
                    style = barStyle,
                    maxValue = maxValue,
                    index = index,
                    modifier = slotModifier
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth(barWidthFraction)
                )
            }
        }
    }
}

private fun getValidStyle(style: SimpleBarChartStyle, index: Int): BarStyle {
    val styleFromList = style.barStyles.getOrNull(index)

    return if (styleFromList == null) {
        requireNotNull(style.defaultBarStyle)
        style.defaultBarStyle
    } else {
        styleFromList
    }
}