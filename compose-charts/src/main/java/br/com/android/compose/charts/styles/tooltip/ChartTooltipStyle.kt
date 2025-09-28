package br.com.android.compose.charts.styles.tooltip

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.android.compose.charts.styles.text.ChartTextStyle

/**
 * Define o estilo para a dica de ferramenta (tooltip) de um gráfico.
 *
 * @property textStyle O estilo do texto dentro da tooltip.
 * @property backgroundColor A cor de fundo da tooltip.
 * @property shape A forma do container da tooltip.
 * @property horizontalPadding O preenchimento horizontal interno.
 * @property verticalPadding O preenchimento vertical interno.
 * @property shadowElevation A elevação da sombra para a tooltip.
 *
 * @author Nikolas Luiz Schmitt
 */
data class ChartTooltipStyle(
    val textStyle: ChartTextStyle,
    val backgroundColor: Color = Color.Black,
    val shape: Shape = RoundedCornerShape(4.dp),
    val horizontalPadding: Dp = 0.dp,
    val verticalPadding: Dp = 0.dp,
    val shadowElevation: Dp = 2.dp
)