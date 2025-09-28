package br.com.android.ui.compose.components.video.state

import android.graphics.Bitmap

/**
 * Representa o estado do componente [br.com.android.ui.compose.components.video.components.VideoGallery].
 *
 * @property title O título exibido no cabeçalho da galeria.
 * @property videoPaths A lista de caminhos de arquivo para os vídeos na galeria.
 * @property thumbCache Um mapa que armazena as miniaturas (thumbnails) dos vídeos em cache, usando o caminho do arquivo como chave.
 * @property isScrollEnabled Controla se o scroll (no modo carrossel) está habilitado.
 * @property viewMode O modo de visualização atual da galeria ([VideoGalleryViewMode.COLLAPSED] ou [VideoGalleryViewMode.EXPANDED]).
 * @property onViewModeChange Callback invocado quando o modo de visualização é alterado.
 * @property showDeleteButton Controla a visibilidade dos botões de exclusão nas miniaturas.
 *
 * @author Nikolas Luiz Schmitt
 */
data class VideoGalleryState(
    val title: String = "",
    val videoPaths: List<String> = emptyList(),
    val thumbCache: Map<String, Bitmap?> = emptyMap(),
    val isScrollEnabled: Boolean = true,
    val viewMode: VideoGalleryViewMode = VideoGalleryViewMode.COLLAPSED,
    val onViewModeChange: (VideoGalleryViewMode) -> Unit = { },
    val showDeleteButton: Boolean = true,
)