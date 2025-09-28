package br.com.android.ui.compose.components.video.state

/**
 * Enum que define os possíveis modos de visualização para a [VideoGallery].
 *
 * @author Nikolas Luiz Schmitt
 */
enum class VideoGalleryViewMode {
    /**
     * Modo recolhido, geralmente exibindo os vídeos em um carrossel horizontal.
     */
    COLLAPSED,

    /**
     * Modo expandido, geralmente exibindo os vídeos em uma grade vertical que ocupa mais espaço.
     */
    EXPANDED
}