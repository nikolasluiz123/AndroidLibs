package br.com.android.ui.compose.components.tabs

import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import br.com.android.ui.compose.components.divider.BaseHorizontalDivider
import br.com.android.ui.compose.components.styles.TabTitleTextStyle
import br.com.android.ui.compose.components.tabs.state.interfaces.IEnumTab
import br.com.android.ui.compose.components.tabs.state.TabState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Componente que encapsula o [TabRow] do Material 3 para criar uma linha de abas (tabs).
 *
 * Ele é projetado para trabalhar em conjunto com um [BaseHorizontalPager] e um [TabState],
 * gerenciando a seleção de abas e a navegação.
 *
 * @param modifier O [Modifier] a ser aplicado ao componente.
 * @param tabState O estado que contém a lista de abas e o comportamento de seleção.
 * @param coroutineScope Um [CoroutineScope] para controlar a animação de scroll do pager.
 * @param pagerState O estado do pager associado para sincronização.
 * @param onTabClick Callback opcional invocado quando uma aba é clicada.
 * @param containerColor Cor de fundo da [TabRow].
 * @param contentColor Cor do conteúdo (texto e ícones) das abas.
 * @param selectedIndicatorColor Cor do indicador da aba selecionada.
 * @param tabTitleTextStyle Estilo do texto para o título das abas.
 * @param enabledTextColor Cor do texto para abas habilitadas.
 * @param disabledTextColor Cor do texto para abas desabilitadas.
 *
 * @see [BaseHorizontalPager]
 * @see [TabState]
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun BaseTabRow(
    modifier: Modifier = Modifier,
    tabState: TabState,
    coroutineScope: CoroutineScope,
    pagerState: PagerState,
    onTabClick: (IEnumTab) -> Unit = { },
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    selectedIndicatorColor: Color = MaterialTheme.colorScheme.inversePrimary,
    tabTitleTextStyle: TextStyle = TabTitleTextStyle,
    enabledTextColor: Color = MaterialTheme.colorScheme.onPrimary,
    disabledTextColor: Color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = tabState.tabs.first { it.selected }.enum.index,
        containerColor = containerColor,
        contentColor = contentColor,
        divider = {
            BaseHorizontalDivider()
        },
        indicator = { tabPositions ->
            val selectedIndex = tabState.tabs.first { it.selected }.enum.index

            if (selectedIndex < tabPositions.size) {
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedIndex]),
                    color = selectedIndicatorColor
                )
            }
        }
    ) {
        tabState.tabs.forEach { tabToCreate ->
            Tab(
                modifier = Modifier,
                selected = tabToCreate.selected,
                onClick = {
                    onTabClick(tabToCreate.enum)
                    tabState.onSelectTab(tabToCreate)

                    coroutineScope.launch {
                        pagerState.scrollToPage(tabToCreate.enum.index)
                    }
                },
                text = {
                    Text(
                        text = stringResource(id = tabToCreate.enum.labelResId),
                        style = tabTitleTextStyle,
                        color = if (tabToCreate.enabled) enabledTextColor else disabledTextColor
                    )
                },
                enabled = tabToCreate.enabled
            )
        }
    }
}