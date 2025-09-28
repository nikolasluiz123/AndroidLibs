package br.com.android.ui.compose.components.bottomsheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import br.com.android.ui.compose.components.bottomsheet.interfaces.IBottomSheetItem
import br.com.android.ui.compose.components.bottomsheet.interfaces.IEnumOptionsBottomSheet
import br.com.android.ui.compose.components.styles.BottomSheetItemTextStyle

/**
 * Um componente de bottom sheet modal genérico que exibe uma lista de itens.
 * Este Composable foi projetado para ser reutilizável, permitindo a exibição de diferentes conjuntos de opções
 * que implementam a interface [IBottomSheetItem].
 *
 * @param T O tipo do enum que representa as opções do bottom sheet, que deve implementar [IEnumOptionsBottomSheet].
 * @param items A lista de itens a serem exibidos no bottom sheet. Cada item deve ser uma implementação de [IBottomSheetItem].
 * @param onDismissRequest Uma lambda que é chamada quando o usuário solicita o fechamento do bottom sheet (por exemplo, tocando fora dele ou pressionando o botão "voltar").
 * @param onItemClickListener Uma lambda que é chamada quando o usuário clica em um item da lista. O enum correspondente ao item clicado é passado como parâmetro.
 * @param modifier O [Modifier] a ser aplicado ao [ModalBottomSheet].
 * @param containerColor A cor de fundo do contêiner do bottom sheet.
 * @param contentColor A cor do conteúdo (texto e ícones) dentro do bottom sheet.
 * @param itemTextStyle O estilo do texto para os rótulos dos itens no bottom sheet.
 *
 * @see IBottomSheetItem
 * @see IEnumOptionsBottomSheet
 * @author Nikolas Luiz Schmitt
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : IEnumOptionsBottomSheet> BottomSheet(
    items: List<IBottomSheetItem<T>>,
    onDismissRequest: () -> Unit,
    onItemClickListener: (T) -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainer,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    itemTextStyle: TextStyle = BottomSheetItemTextStyle
) {
    val bottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        sheetState = bottomSheetState,
        containerColor = containerColor,
        contentColor = contentColor
    ) {
        LazyColumn {
            items(items) { item ->
                ListItem(
                    headlineContent = {
                        Text(
                            text = stringResource(id = item.labelResId),
                            style = itemTextStyle,
                            color = contentColor
                        )
                    },
                    leadingContent = item.iconResId?.let { iconResId ->
                        {
                            Icon(
                                painter = painterResource(id = iconResId),
                                contentDescription = item.iconDescriptionResId?.let { stringResource(id = it) },
                                tint = contentColor
                            )
                        }
                    },
                    colors = ListItemDefaults.colors(containerColor = containerColor),
                    modifier = Modifier.clickable {
                        onItemClickListener(item.option)
                    }
                )
            }
        }
    }
}