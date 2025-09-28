package br.com.android.compose.charts.styles.bar

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.android.compose.charts.styles.IAnimatedStyle
import br.com.android.compose.charts.styles.tooltip.ChartTooltipStyle

/**
 * Define o estilo de uma única barra em um gráfico de barras.
 *
 * @property fillColor A cor de preenchimento da barra.
 * @property borderColor A cor da borda da barra.
 * @property borderWidth A espessura da borda da barra.
 * @property shape A forma da barra (ex: cantos arredondados).
 * @property tooltipStyle O estilo para a dica de ferramenta (tooltip) exibida acima da barra, ou `null` para desabilitar.
 *
 * @author Nikolas Luiz Schmitt
 */
data class BarStyle(
    val fillColor: Color,
    val borderColor: Color = Color.Transparent,
    val borderWidth: Dp = 1.dp,
    val shape: Shape = RoundedCornerShape(4.dp, 4.dp, 0.dp, 0.dp),
    val tooltipStyle: ChartTooltipStyle? = null,
    override val animationDuration: Int = 200,
    override val animationDelay: Long = 100L
): IAnimatedStyle