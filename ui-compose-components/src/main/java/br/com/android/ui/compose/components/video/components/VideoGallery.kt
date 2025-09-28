package br.com.android.ui.compose.components.video.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import br.com.android.ui.compose.components.R
import br.com.android.ui.compose.components.divider.BaseHorizontalDivider
import br.com.android.ui.compose.components.list.EmptyState
import br.com.android.ui.compose.components.styles.VideoGalleryTitleStyle
import br.com.android.ui.compose.components.video.callbacks.OnVideoClick
import br.com.android.ui.compose.components.video.callbacks.OnVideoDeleteClick
import br.com.android.ui.compose.components.video.state.VideoGalleryState
import br.com.android.ui.compose.components.video.state.VideoGalleryViewMode

/**
 * Um componente de galeria de vídeos que pode ser exibido em modo recolhido ou expandido.
 *
 * A altura do componente é animada ao alternar entre os modos de visualização. Exibe as miniaturas
 * de vídeo e uma barra de ações na parte inferior.
 *
 * @param state O estado que controla a galeria de vídeos.
 * @param iconTintWithThumbnail A cor dos ícones (play, delete) quando há uma thumbnail de fundo.
 * @param iconTintWithoutThumbnail A cor dos ícones quando não há thumbnail.
 * @param modifier O [Modifier] a ser aplicado ao container da galeria.
 * @param emptyMessage Mensagem a ser exibida quando a galeria estiver vazia.
 * @param onVideoClick Callback para o evento de clique em uma miniatura de vídeo.
 * @param onVideoDeleteClick Callback para o evento de clique no ícone de exclusão.
 * @param actions Slot para adicionar ações personalizadas na barra inferior.
 * @param backgroundColor Cor de fundo da galeria.
 * @param shape A forma do container da galeria.
 *
 * @see [VideoGalleryState]
 * @see [ThumbnailsViewer]
 * @see [ActionsBottomBar]
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun VideoGallery(
    state: VideoGalleryState,
    iconTintWithThumbnail: Color,
    iconTintWithoutThumbnail: Color,
    modifier: Modifier = Modifier,
    emptyMessage: String = stringResource(id = R.string.video_gallery_empty_message),
    onVideoClick: OnVideoClick? = null,
    onVideoDeleteClick: OnVideoDeleteClick? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    shape: Shape = MaterialTheme.shapes.extraSmall,
) {
    val screenHeight = LocalWindowInfo.current.containerSize.height.dp

    val isExpanded = state.viewMode == VideoGalleryViewMode.EXPANDED
    val targetHeight = if (isExpanded) screenHeight else 210.dp

    val animatedHeight by animateDpAsState(
        targetValue = targetHeight,
        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing),
        label = "GalleryHeight"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(animatedHeight)
            .background(color = backgroundColor, shape = shape)
    ) {
        VideoGalleryHeader(
            title = state.title,
            modifier = Modifier.fillMaxWidth()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true)
        ) {
            if (state.videoPaths.isEmpty()) {
                EmptyState(emptyMessage)
            } else {
                ThumbnailsViewer(
                    state = state,
                    onVideoClick = onVideoClick,
                    onVideoDeleteClick = onVideoDeleteClick,
                    iconTintWithThumbnail = iconTintWithThumbnail,
                    iconTintWithoutThumbnail = iconTintWithoutThumbnail
                )
            }
        }

        BaseHorizontalDivider()

        ActionsBottomBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            state = state,
            actions = actions
        )
    }
}

/**
 * Cabeçalho para a [VideoGallery], exibindo um título centralizado.
 *
 * @param title O texto do título.
 * @param modifier O [Modifier] a ser aplicado ao [Box] do cabeçalho.
 * @param textStyle O estilo do texto para o título.
 */
@Composable
fun VideoGalleryHeader(
    title: String,
    modifier: Modifier,
    textStyle: TextStyle = VideoGalleryTitleStyle
) {
    Box(
        modifier
            .padding(top = 16.dp, bottom = 4.dp, start = 8.dp, end = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = textStyle
        )
    }
}