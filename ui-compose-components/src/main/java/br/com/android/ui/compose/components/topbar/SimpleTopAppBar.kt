package br.com.android.ui.compose.components.topbar

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import br.com.android.ui.compose.components.styles.TopAppBarSubtitleTextStyle
import br.com.android.ui.compose.components.styles.TopAppBarTitleTextStyle

/**
 * Uma implementação de [BaseTopAppBar] que simplifica a criação de uma barra
 * com título e subtítulo.
 *
 * @param title O texto do título principal da barra.
 * @param subtitle O texto opcional do subtítulo, exibido abaixo do título.
 * @param onLogoutClick Ação ao clicar no item de menu Logout.
 * @param onBackClick Ação ao clicar no ícone de navegação.
 * @param actions Ações exibidas à direita da barra.
 * @param menuItems Itens de menu exibidos dentro do menu de opções.
 * @param colors Cores da barra.
 * @param showNavigationIcon Flag para exibir ou ocultar o ícone de navegação.
 * @param customNavigationIcon Um Composable opcional para substituir o ícone de navegação padrão.
 * @param showMenu Flag para exibir ou ocultar o menu de opções (três pontos).
 * @param titleTextStyle O estilo do texto para o título.
 * @param subtitleTextStyle O estilo do texto para o subtítulo.
 *
 * @see [BaseTopAppBar]
 * @author Nikolas Luiz Schmitt
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopAppBar(
    title: String,
    subtitle: String? = null,
    onLogoutClick: () -> Unit = { },
    onBackClick: () -> Unit = { },
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
    titleTextStyle: TextStyle = TopAppBarTitleTextStyle,
    subtitleTextStyle: TextStyle = TopAppBarSubtitleTextStyle
) {
    BaseTopAppBar(
        title = {
            Column {
                Text(
                    text = title,
                    style = titleTextStyle,
                )

                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        style = subtitleTextStyle,
                    )
                }
            }
        },
        colors = colors,
        actions = actions,
        menuItems = menuItems,
        showNavigationIcon = showNavigationIcon,
        customNavigationIcon = customNavigationIcon,
        onBackClick = onBackClick,
        onLogoutClick = onLogoutClick,
        showMenu = showMenu
    )
}