package br.com.android.ui.compose.components.video.components

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import br.com.android.ui.compose.components.R

/**
 * Exibe um ícone de "Play" sobreposto, geralmente usado em cima de uma [VideoThumbnail].
 *
 * A cor do ícone é ajustada dinamicamente para garantir um bom contraste, dependendo
 * da presença de uma thumbnail de fundo.
 *
 * @param hasThumbnail Indica se a thumbnail do vídeo está sendo exibida.
 * @param iconTintWithThumbnail A cor do ícone quando há uma thumbnail de fundo.
 * @param iconTintWithoutThumbnail A cor do ícone quando não há thumbnail (fundo sólido).
 * @param modifier O [Modifier] a ser aplicado ao [Icon].
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun PlayIconOverlay(
    hasThumbnail: Boolean,
    iconTintWithThumbnail: Color,
    iconTintWithoutThumbnail: Color,
    modifier: Modifier = Modifier
) {
    Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_play_circle_filled_32dp),
        contentDescription = stringResource(R.string.play_icon_content_description),
        tint = if (hasThumbnail) iconTintWithThumbnail else iconTintWithoutThumbnail,
    )
}