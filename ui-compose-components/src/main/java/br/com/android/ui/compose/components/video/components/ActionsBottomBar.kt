package br.com.android.ui.compose.components.video.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.android.ui.compose.components.video.state.VideoGalleryState

/**
 * Barra de ações inferior para a [VideoGallery].
 *
 * Contém o botão para alterar o modo de visualização e um espaço para ações personalizadas.
 *
 * @param modifier O [Modifier] a ser aplicado ao layout da [Row].
 * @param state O estado da galeria de vídeos.
 * @param actions Um slot para adicionar ações personalizadas (botões, etc.) à direita da barra.
 *
 * @see [VideoGallery]
 * @see [IconButtonViewModeChange]
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
internal fun ActionsBottomBar(
    modifier: Modifier,
    state: VideoGalleryState,
    actions: @Composable (RowScope.() -> Unit)
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButtonViewModeChange(state)

        Spacer(modifier = Modifier.weight(1f))

        Row(content = actions)
    }
}