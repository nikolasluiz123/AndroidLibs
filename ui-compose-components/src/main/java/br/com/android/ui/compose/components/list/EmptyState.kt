package br.com.android.ui.compose.components.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.android.ui.compose.components.styles.LabelTextStyle

/**
 * Um componente para exibir uma mensagem quando uma lista está vazia.
 *
 * @param emptyMessage A mensagem a ser exibida.
 * @param color A cor do texto da mensagem.
 * @param textStyle O estilo do texto da mensagem.
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun EmptyState(
    emptyMessage: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textStyle: TextStyle = LabelTextStyle
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = emptyMessage,
            style = textStyle,
            textAlign = TextAlign.Center,
            color = color
        )
    }
}