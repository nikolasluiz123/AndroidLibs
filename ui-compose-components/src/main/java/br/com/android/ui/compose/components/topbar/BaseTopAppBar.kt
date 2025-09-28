package br.com.android.ui.compose.components.topbar

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import br.com.android.ui.compose.components.buttons.icons.IconButtonArrowBack
import br.com.android.ui.compose.components.buttons.icons.MenuIconButton

/**
 * Componente base para a barra de aplicativos superior (TopAppBar) padrão do projeto.
 *
 * Oferece uma estrutura flexível com slots para título, ações, e um ícone de navegação
 * customizável.
 *
 * @param title O conteúdo Composable para o título da barra.
 * @param onBackClick Ação executada quando o ícone de navegação padrão (seta para trás) é clicado.
 * @param onLogoutClick Ação executada quando o item de menu "Logout" (se houver) é clicado.
 * @param actions Conteúdo Composable para as ações exibidas à direita do título.
 * @param menuItems Conteúdo Composable para os itens dentro do menu de opções (se exibido).
 * @param colors Cores personalizadas para a TopAppBar.
 * @param showNavigationIcon Controla a visibilidade do ícone de navegação.
 * @param customNavigationIcon Um Composable opcional para substituir o ícone de navegação padrão.
 * @param showMenu Controla a visibilidade do menu de opções (três pontos).
 * @param windowInsets Permite ajustar os insets da janela para a TopAppBar.
 *
 * @see [SimpleTopAppBar]
 * @author Nikolas Luiz Schmitt
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopAppBar(
    title: @Composable () -> Unit,
    onBackClick: () -> Unit,
    onLogoutClick: () -> Unit = { },
    actions: @Composable () -> Unit = { },
    menuItems: @Composable () -> Unit = { },
    colors: TopAppBarColors = TopAppBarDefaults.mediumTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary,
        titleContentColor = MaterialTheme.colorScheme.onPrimary,
        actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
    ),
    showNavigationIcon: Boolean = true,
    customNavigationIcon: (@Composable () -> Unit)? = null,
    showMenu: Boolean = false,
    windowInsets: WindowInsets = WindowInsets(0.dp),
) {
    TopAppBar(
        title = title,
        colors = colors,
        windowInsets = windowInsets,
        navigationIcon = {
            if (showNavigationIcon) {
                if (customNavigationIcon != null) {
                    customNavigationIcon()
                } else {
                    IconButtonArrowBack(
                        onClick = onBackClick,
                    )
                }
            }
        },
        actions = {
            actions()

            if (showMenu) {
                MenuIconButton(menuItems)
            }
        }
    )
}