package br.com.android.ui.compose.components.divider

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Um divisor horizontal básico que pode ser usado para separar elementos em um layout.
 *
 * @param modifier O [Modifier] a ser aplicado ao divisor.
 * @param color A cor do divisor.
 * @param thickness A espessura do divisor.
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun BaseHorizontalDivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.outline,
    thickness: Dp = 0.5.dp
) {
    HorizontalDivider(
        modifier = modifier,
        color = color,
        thickness = thickness
    )
}