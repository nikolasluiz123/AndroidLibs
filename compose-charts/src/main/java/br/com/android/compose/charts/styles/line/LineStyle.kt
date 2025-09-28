package br.com.android.compose.charts.styles.line

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.android.compose.charts.styles.IAnimatedStyle
import br.com.android.compose.charts.styles.line.enums.LineType
import br.com.android.compose.charts.styles.tooltip.ChartTooltipStyle

/**
 * Define o estilo de uma única linha em um [br.com.android.compose.charts.composables.line.LineChart].
 *
 * @property color A cor da linha e dos pontos de dados.
 * @property width A espessura da linha.
 * @property lineType O tipo de linha (reta ou curva).
 * @property showDataPoints Controla a visibilidade dos círculos nos pontos de dados.
 * @property dataPointRadius O raio dos círculos dos pontos de dados.
 * @property tooltipStyle O estilo para a dica de ferramenta (tooltip) exibida nos pontos de dados, ou `null` para desabilitar.
 *
 * @author Nikolas Luiz Schmitt
 */
data class LineStyle(
    val color: Color,
    val width: Dp = 3.dp,
    val lineType: LineType = LineType.CURVED,
    val showDataPoints: Boolean = true,
    val dataPointRadius: Dp = 4.dp,
    val tooltipStyle: ChartTooltipStyle? = null,
    override val animationDuration: Int = 1000,
    override val animationDelay: Long = 200L
): IAnimatedStyle