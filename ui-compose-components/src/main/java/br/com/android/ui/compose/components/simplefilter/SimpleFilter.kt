package br.com.android.ui.compose.components.simplefilter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import br.com.android.ui.compose.components.R
import br.com.android.ui.compose.components.styles.ValueTextStyle

/**
 * Componente de barra de pesquisa simplificada que utiliza um [SimpleFilterState] para gerenciar seu estado.
 *
 * Esta é uma sobrecarga que facilita o uso do componente ao centralizar o estado em um único objeto.
 *
 * @param state O estado que controla o comportamento e os dados do filtro.
 * @param placeholderResId O ID do recurso de string para o texto do placeholder.
 * @param modifier O [Modifier] a ser aplicado ao [SearchBar].
 * @param content O conteúdo a ser exibido quando a barra de pesquisa está expandida (ativa).
 *
 * @see [SimpleFilterState]
 * @author Nikolas Luiz Schmitt
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleFilter(
    state: SimpleFilterState,
    placeholderResId: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    SimpleFilter(
        expanded = state.simpleFilterExpanded,
        quickFilter = state.quickFilter,
        placeholderResId = placeholderResId,
        onSimpleFilterChange = state.onSimpleFilterChange,
        onExpandedChange = state.onExpandedChange,
        modifier = modifier,
        content = content
    )
}

/**
 * Componente de barra de pesquisa simplificada, baseado no [SearchBar] do Material 3.
 *
 * Oferece uma interface de filtro com callbacks para mudanças de texto e estado de expansão.
 *
 * @param expanded Controla se a barra de pesquisa está no estado expandido (ativa).
 * @param quickFilter O texto de consulta atual no campo de pesquisa.
 * @param placeholderResId O ID do recurso de string para o texto do placeholder.
 * @param onSimpleFilterChange Callback invocado quando o texto da consulta é alterado.
 * @param onExpandedChange Callback invocado quando o estado de expansão é alterado.
 * @param modifier O [Modifier] a ser aplicado ao [SearchBar].
 * @param containerColor A cor de fundo do container da barra de pesquisa.
 * @param contentColor A cor do conteúdo principal (ícones, texto).
 * @param placeholderTextStyle O estilo de texto para o placeholder.
 * @param content O conteúdo a ser exibido quando a barra de pesquisa está expandida.
 *
 * @author Nikolas Luiz Schmitt
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleFilter(
    expanded: Boolean,
    quickFilter: String,
    placeholderResId: Int,
    onSimpleFilterChange: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    placeholderTextStyle: TextStyle = ValueTextStyle,
    content: @Composable () -> Unit
) {
    SearchBar(
        windowInsets = WindowInsets(top = 0.dp),
        modifier = modifier.background(color = containerColor),
        colors = SearchBarDefaults.colors(
            containerColor = containerColor,
            dividerColor = Color.Transparent,
        ),
        shape = SearchBarDefaults.fullScreenShape,
        inputField = {
            SearchBarDefaults.InputField(
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search_24dp),
                        contentDescription = null,
                        tint = contentColor
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(placeholderResId),
                        style = placeholderTextStyle,
                        color = contentColor
                    )
                },
                query = quickFilter,
                onQueryChange = {
                    onSimpleFilterChange(it)
                },
                onSearch = {
                    onSimpleFilterChange(it)
                },
                expanded = expanded,
                onExpandedChange = onExpandedChange
            )
        },
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        content = {
            content()
        }
    )
}