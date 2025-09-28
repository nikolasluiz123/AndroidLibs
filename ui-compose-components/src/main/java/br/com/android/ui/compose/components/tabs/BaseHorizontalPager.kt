package br.com.android.ui.compose.components.tabs

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.android.ui.compose.components.tabs.state.interfaces.IEnumTab
import br.com.android.ui.compose.components.tabs.state.Tab
import br.com.android.ui.compose.components.tabs.state.TabState

/**
 * Um componente wrapper para o [HorizontalPager] que facilita a integração com um [BaseTabRow].
 *
 * Ele gerencia a navegação por gestos (scroll) entre as páginas, que correspondem às abas,
 * e exibe o conteúdo específico de cada uma.
 *
 * @param pagerState O estado que controla o [HorizontalPager], como a página atual.
 * @param tabState O estado que gerencia as abas ([TabState]).
 * @param modifier O [Modifier] a ser aplicado ao componente.
 * @param onScroll Callback invocado quando ocorre o scroll entre as páginas, informando a aba de destino.
 * @param content O conteúdo Composable a ser renderizado para uma página/aba específica, recebendo o índice como parâmetro.
 *
 * @see [BaseTabRow]
 * @see [TabState]
 * @see [PagerState]
 *
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun BaseHorizontalPager(
    pagerState: PagerState,
    tabState: TabState,
    modifier: Modifier = Modifier,
    onScroll: (IEnumTab) -> Unit = { },
    content: @Composable (index: Int) -> Unit
) {
    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        pageSpacing = 0.dp,
        userScrollEnabled = getUserScrollEnabled(tabState.tabs),
        reverseLayout = false,
        contentPadding = PaddingValues(0.dp),
        pageSize = PageSize.Fill,
        flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
        key = null,
        pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
            state = pagerState,
            orientation = Orientation.Horizontal
        )
    ) { index ->
        onScroll(tabState.tabs[index].enum)
        tabState.onSelectTab(tabState.tabs[index])
        content(index)
    }
}


/**
 * Determina se o scroll do [HorizontalPager] deve ser habilitado.
 *
 * A lógica permite o scroll apenas se a aba anterior ou a próxima estiver habilitada,
 * prevenindo a navegação para abas desabilitadas.
 *
 * @param tabs A lista de abas.
 * @return `true` se o scroll deve ser permitido, `false` caso contrário.
 */
private fun getUserScrollEnabled(tabs: List<Tab>): Boolean {
    val tabSelected = tabs.first { it.selected }
    val nextTab = tabs.getOrNull(tabs.indexOf(tabSelected) + 1)
    val previousTab = tabs.getOrNull(tabs.indexOf(tabSelected) - 1)

    return nextTab?.enabled == true || previousTab?.enabled == true
}