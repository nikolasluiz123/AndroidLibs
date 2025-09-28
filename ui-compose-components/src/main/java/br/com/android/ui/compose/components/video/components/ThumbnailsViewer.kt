package br.com.android.ui.compose.components.video.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.android.ui.compose.components.video.callbacks.OnVideoClick
import br.com.android.ui.compose.components.video.callbacks.OnVideoDeleteClick
import br.com.core.android.compose.utils.extensions.openVideoPlayer
import br.com.android.ui.compose.components.video.state.VideoGalleryState
import br.com.android.ui.compose.components.video.state.VideoGalleryViewMode

/**
 * Componente interno que decide qual layout usar para exibir as miniaturas de vídeo
 * (carrossel ou grade) com base no [VideoGalleryViewMode].
 *
 * @param state O estado da galeria de vídeos.
 * @param onVideoClick Callback para o clique em um vídeo.
 * @param onVideoDeleteClick Callback para o clique na exclusão de um vídeo.
 * @param iconTintWithThumbnail Cor do ícone quando há thumbnail.
 * @param iconTintWithoutThumbnail Cor do ícone quando não há thumbnail.
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
internal fun ThumbnailsViewer(
    state: VideoGalleryState,
    onVideoClick: OnVideoClick? = null,
    onVideoDeleteClick: OnVideoDeleteClick? = null,
    iconTintWithThumbnail: Color,
    iconTintWithoutThumbnail: Color

) {
    when (state.viewMode) {
        VideoGalleryViewMode.COLLAPSED -> {
            VideosCarousel(
                state = state,
                onVideoClick = onVideoClick,
                onVideoDeleteClick = onVideoDeleteClick,
                iconTintWithThumbnail = iconTintWithThumbnail,
                iconTintWithoutThumbnail = iconTintWithoutThumbnail
            )
        }

        VideoGalleryViewMode.EXPANDED -> {
            VideosGrid(
                state = state,
                onVideoClick = onVideoClick,
                onVideoDeleteClick = onVideoDeleteClick,
                iconTintWithThumbnail = iconTintWithThumbnail,
                iconTintWithoutThumbnail = iconTintWithoutThumbnail
            )
        }
    }
}

/**
 * Exibe as miniaturas de vídeo em um carrossel horizontal ([LazyRow]).
 */
@Composable
private fun VideosCarousel(
    state: VideoGalleryState,
    onVideoClick: OnVideoClick?,
    onVideoDeleteClick: OnVideoDeleteClick?,
    iconTintWithThumbnail: Color,
    iconTintWithoutThumbnail: Color
) {
    val context = LocalContext.current

    LazyRow(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        userScrollEnabled = state.isScrollEnabled
    ) {
        items(state.videoPaths) { videoPath ->
            VideoThumbnail(
                bitmap = state.thumbCache[videoPath],
                showDeleteButton = state.showDeleteButton,
                iconTintWithThumbnail = iconTintWithThumbnail,
                iconTintWithoutThumbnail = iconTintWithoutThumbnail,
                onVideoClick = {
                    onVideoClick?.onExecute(
                        filePath = videoPath,
                        onOpenVideo = context::openVideoPlayer
                    )
                },
                onDeleteVideoClick = {
                    onVideoDeleteClick?.onExecute(videoPath)
                }
            )
        }
    }
}

/**
 * Exibe as miniaturas de vídeo em uma grade vertical ([LazyVerticalGrid]).
 */
@Composable
private fun VideosGrid(
    state: VideoGalleryState,
    onVideoClick: OnVideoClick?,
    onVideoDeleteClick: OnVideoDeleteClick?,
    iconTintWithThumbnail: Color,
    iconTintWithoutThumbnail: Color
) {
    val context = LocalContext.current

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
    ) {
        items(state.videoPaths) { videoPath ->
            VideoThumbnail(
                bitmap = state.thumbCache[videoPath],
                onVideoClick = {
                    onVideoClick?.onExecute(
                        filePath = videoPath,
                        onOpenVideo = context::openVideoPlayer
                    )
                },
                onDeleteVideoClick = {
                    onVideoDeleteClick?.onExecute(videoPath)
                },
                modifier = Modifier.fillMaxWidth(),
                iconTintWithThumbnail = iconTintWithThumbnail,
                iconTintWithoutThumbnail = iconTintWithoutThumbnail
            )
        }
    }
}