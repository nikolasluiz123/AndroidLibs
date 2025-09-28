package br.com.android.ui.compose.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import br.com.android.ui.compose.components.styles.ValueTextStyle

/**
 * Um botão em formato de cartão quadrado, ideal para menus ou dashboards
 * onde cada opção é representada por um ícone e um rótulo.
 *
 * @param iconResId O ID do recurso do drawable para o ícone a ser exibido.
 * @param label O texto do rótulo a ser exibido abaixo do ícone.
 * @param modifier O [Modifier] a ser aplicado ao cartão do botão.
 * @param onClick A lambda a ser executada quando o botão for clicado.
 * @param enabled Um booleano que indica se o botão está habilitado e pode ser interagido.
 * @param colors As cores a serem usadas para o cartão em diferentes estados.
 * @param shape A forma do cartão do botão.
 * @param buttonLabelTextStyle O estilo do texto para o rótulo do botão.
 * @param contentColor A cor a ser aplicada ao ícone e ao texto do botão.
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun SquaredButton(
    iconResId: Int,
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    colors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
        disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f)
    ),
    shape: RoundedCornerShape = RoundedCornerShape(4.dp),
    buttonLabelTextStyle: TextStyle = ValueTextStyle,
    contentColor: Color = MaterialTheme.colorScheme.onSecondaryContainer
) {
    Card(
        modifier
            .fillMaxSize(),
        colors = colors,
        elevation = CardDefaults.cardElevation(defaultElevation = if (enabled) 16.dp else 0.dp),
        shape = shape,
    ) {
        ConstraintLayout(
            Modifier
                .fillMaxSize()
                .clickable(onClick = onClick, enabled = enabled)
        ) {
            val (columnRef) = createRefs()

            Column(
                Modifier.constrainAs(columnRef) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .alpha(if (enabled) 1f else 0.5f),
                    painter = painterResource(id = iconResId),
                    colorFilter = ColorFilter.tint(color = contentColor),
                    contentDescription = null,
                )

                Text(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .alpha(if (enabled) 1f else 0.5f),
                    text = label,
                    style = buttonLabelTextStyle,
                    color = contentColor
                )
            }
        }
    }
}