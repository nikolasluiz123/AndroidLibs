package br.com.android.compose.charts.styles.text

import android.graphics.Paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.android.compose.charts.styles.text.enums.LongLabelStrategy

/**
 * Define o estilo para textos desenhados em um `Canvas` dentro dos gráficos.
 *
 * @property color A cor do texto.
 * @property fontSize O tamanho da fonte.
 * @property fontWeight O peso da fonte (ex: Normal, Negrito).
 * @property textAlign O alinhamento do texto.
 * @property padding O preenchimento ao redor do texto.
 * @property longLabelStrategy A estratégia a ser usada quando um rótulo de texto é muito longo
 * para o espaço disponível.
 *
 * @author Nikolas Luiz Schmitt
 */
data class ChartTextStyle(
    val color: Color = Color.Black,
    val fontSize: TextUnit = 12.sp,
    val fontWeight: FontWeight = FontWeight.Normal,
    val textAlign: Paint.Align = Paint.Align.CENTER,
    val padding: Dp = 4.dp,
    val longLabelStrategy: LongLabelStrategy = LongLabelStrategy.MultiLine
)