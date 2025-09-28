package br.com.android.ui.compose.components.video.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import br.com.android.ui.compose.components.R

/**
 * Um [IconButton] para excluir um vídeo, geralmente sobreposto a um [VideoThumbnail].
 *
 * A cor do ícone se adapta para garantir a visibilidade, dependendo se há uma
 * thumbnail de fundo ou não.
 *
 * @param hasThumbnail Indica se existe uma thumbnail de vídeo sendo exibida.
 * @param iconTintWithThumbnail A cor do ícone quando há uma thumbnail de fundo.
 * @param iconTintWithoutThumbnail A cor do ícone quando não há thumbnail.
 * @param modifier O [Modifier] a ser aplicado ao [IconButton].
 * @param onClick Ação a ser executada ao clicar no ícone.
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun DeleteVideoIcon(
    hasThumbnail: Boolean,
    iconTintWithThumbnail: Color,
    iconTintWithoutThumbnail: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_cancel_24dp),
            contentDescription = stringResource(R.string.delete_icon_content_description),
            tint = if (hasThumbnail) iconTintWithThumbnail else iconTintWithoutThumbnail,
        )
    }
}