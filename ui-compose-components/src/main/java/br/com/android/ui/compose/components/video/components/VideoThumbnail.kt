package br.com.android.ui.compose.components.video.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

/**
 * Exibe a miniatura (thumbnail) de um vídeo.
 *
 * Sobrepõe um ícone de "play" e, opcionalmente, um ícone de exclusão.
 *
 * @param modifier O [Modifier] a ser aplicado ao [Box] container.
 * @param iconTintWithThumbnail A cor dos ícones quando há uma thumbnail.
 * @param iconTintWithoutThumbnail A cor dos ícones quando não há thumbnail.
 * @param bitmap O [Bitmap] da thumbnail a ser exibido. Se nulo, um fundo sólido é mostrado.
 * @param showDeleteButton Controla a visibilidade do ícone de exclusão.
 * @param onVideoClick Callback para o clique na thumbnail.
 * @param onDeleteVideoClick Callback para o clique no ícone de exclusão.
 *
 * @see [PlayIconOverlay]
 * @see [DeleteVideoIcon]
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun VideoThumbnail(
    modifier: Modifier = Modifier,
    iconTintWithThumbnail: Color,
    iconTintWithoutThumbnail: Color,
    bitmap: Bitmap? = null,
    showDeleteButton: Boolean = true,
    onVideoClick: () -> Unit = { },
    onDeleteVideoClick: () -> Unit = { },
) {
    Box(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .width(120.dp)
            .height(90.dp)
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.onSurfaceVariant)
            .clickable { onVideoClick() }
    ) {
        bitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        PlayIconOverlay(
            hasThumbnail = bitmap != null,
            modifier = Modifier.align(Alignment.Center),
            iconTintWithThumbnail = iconTintWithThumbnail,
            iconTintWithoutThumbnail = iconTintWithoutThumbnail
        )

        if (showDeleteButton) {
            DeleteVideoIcon(
                hasThumbnail = bitmap != null,
                onClick = onDeleteVideoClick,
                iconTintWithThumbnail = iconTintWithThumbnail,
                iconTintWithoutThumbnail = iconTintWithoutThumbnail,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp)
                    .size(18.dp)
            )
        }
    }
}