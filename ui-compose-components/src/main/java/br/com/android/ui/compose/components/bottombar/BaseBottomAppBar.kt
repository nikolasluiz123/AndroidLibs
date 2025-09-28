package br.com.android.ui.compose.components.bottombar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Um componente de barra de aplicativo inferior padrão que serve como um contêiner para ações e um botão de ação flutuante.
 * Este Composable simplifica a criação de uma [BottomAppBar] com o estilo e as cores do tema do aplicativo.
 *
 * @param modifier O [Modifier] a ser aplicado ao contêiner da barra de aplicativo inferior.
 * @param actions Um slot para `Composable` que define as ações a serem exibidas na barra de aplicativo. Geralmente, são [IconButton]s.
 * @param floatingActionButton Um slot para `Composable` para um [FloatingActionButton] a ser exibido ancorado na barra de aplicativo.
 * @param windowInsets As inserções da janela a serem aplicadas à barra de aplicativo, para lidar com barras do sistema, como a barra de navegação.
 * @param containerColor A cor de fundo do contêiner da barra de aplicativo. O padrão é a cor primária do tema.
 * @param contentColor A cor do conteúdo exibido na barra de aplicativo, como ícones e texto. O padrão é a cor "onPrimary" do tema.
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun BaseBottomAppBar(
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = { },
    floatingActionButton: @Composable () -> Unit = { },
    windowInsets: WindowInsets = WindowInsets(0.dp),
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    BottomAppBar(
        modifier = modifier,
        actions = actions,
        floatingActionButton = floatingActionButton,
        containerColor = containerColor,
        contentColor = contentColor,
        windowInsets = windowInsets
    )
}