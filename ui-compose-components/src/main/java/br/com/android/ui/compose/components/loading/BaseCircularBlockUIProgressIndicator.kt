package br.com.android.ui.compose.components.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout

/**
 * Exibe um indicador de progresso circular que bloqueia a interação do usuário com a tela.
 *
 * Este componente utiliza um [Popup] para sobrepor todo o conteúdo da tela, exibindo uma
 * camada semi-transparente e um [CircularProgressIndicator] no centro. O bloqueio de toques
 * é garantido pela utilização do modifier [pointerInput].
 *
 * @param show Controla a visibilidade do componente. `true` para exibir, `false` para ocultar.
 * @param label Texto opcional a ser exibido abaixo do indicador de progresso.
 * @param modifier O [Modifier] a ser aplicado ao layout do componente.
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun BaseCircularBlockUIProgressIndicator(
    show: Boolean,
    label: String,
    modifier: Modifier = Modifier
) {
    if (show) {
        Popup(alignment = Alignment.Center) {
            ConstraintLayout(modifier = modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
                .pointerInput(Unit) { }
                .zIndex(999999F)) {

                val (loadingRef, textRef) = createRefs()
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.constrainAs(loadingRef) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                )
                Text(
                    label,
                    modifier = Modifier.constrainAs(textRef) {
                        start.linkTo(loadingRef.start)
                        top.linkTo(loadingRef.bottom, margin = 8.dp)
                        end.linkTo(loadingRef.end)
                    },
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )

            }
        }
    }
}