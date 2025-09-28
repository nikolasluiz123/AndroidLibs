package br.com.android.ui.compose.components.video.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import br.com.android.ui.compose.components.R
import br.com.android.ui.compose.components.video.state.VideoGalleryState
import br.com.android.ui.compose.components.video.state.VideoGalleryViewMode

/**
 * Um [IconButton] que alterna o modo de visualização da galeria de vídeos entre
 * [VideoGalleryViewMode.COLLAPSED] e [VideoGalleryViewMode.EXPANDED].
 *
 * @param state O estado atual da galeria de vídeos.
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
internal fun IconButtonViewModeChange(state: VideoGalleryState) {
    IconButton(
        onClick = {
            val viewMode = getNewViewModeBy(state)
            state.onViewModeChange(viewMode)
        }
    ) {
        Icon(
            painter = getVideoGalleryIconViewModelChange(state),
            contentDescription = null
        )
    }
}

/**
 * Determina o próximo [VideoGalleryViewMode] com base no modo atual.
 *
 * @param state O estado atual da galeria de vídeos.
 * @return O novo modo de visualização.
 */
internal fun getNewViewModeBy(state: VideoGalleryState): VideoGalleryViewMode {
    return when (state.viewMode) {
        VideoGalleryViewMode.COLLAPSED -> VideoGalleryViewMode.EXPANDED
        VideoGalleryViewMode.EXPANDED -> VideoGalleryViewMode.COLLAPSED
    }
}

/**
 * Retorna o ícone apropriado (expandir/recolher) com base no modo de visualização atual.
 *
 * @param state O estado atual da galeria de vídeos.
 * @return O [Painter] do ícone a ser exibido.
 */
@Composable
private fun getVideoGalleryIconViewModelChange(state: VideoGalleryState): Painter {
    return if (state.viewMode == VideoGalleryViewMode.COLLAPSED) {
        painterResource(R.drawable.ic_expand_24dp)
    } else {
        painterResource(R.drawable.ic_compress_24dp)
    }
}