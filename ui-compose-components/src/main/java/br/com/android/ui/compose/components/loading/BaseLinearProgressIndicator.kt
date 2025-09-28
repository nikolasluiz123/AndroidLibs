package br.com.android.ui.compose.components.loading

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Componente que exibe um indicador de progresso linear na parte superior da tela.
 *
 * Ideal para representar carregamentos que não bloqueiam a interação do usuário.
 *
 * @param show Controla a visibilidade do componente. `true` para exibir, `false` para ocultar.
 * @param modifier O [Modifier] a ser aplicado ao componente.
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun BaseLinearProgressIndicator(show: Boolean, modifier: Modifier = Modifier) {
    if (show) {
        LinearProgressIndicator(modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.primary)
    }
}