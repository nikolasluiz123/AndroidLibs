package br.com.android.ui.compose.components.fields.dropdown

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import br.com.android.ui.compose.components.buttons.icons.IconButtonArrowDown
import br.com.android.ui.compose.components.fields.dropdown.state.DropDownTextField
import br.com.android.ui.compose.components.fields.text.OutlinedTextFieldValidation
import br.com.android.ui.compose.components.fields.text.state.ITextField

/**
 * Um menu suspenso exposto padrão, que é controlado por um [DropDownTextField].
 *
 * @param T O tipo de valor dos itens do menu.
 * @param field O estado que gerencia o menu suspenso.
 * @param labelResId O ID do recurso de string para o rótulo do campo.
 * @param modifier O [Modifier] a ser aplicado ao [ExposedDropdownMenuBox].
 * @param showClearOption `true` para exibir uma opção para limpar a seleção, `false` caso contrário.
 * @param clearOptionText O texto a ser exibido para a opção de limpar.
 *
 * @see [DropDownTextField]
 * @author Nikolas Luiz Schmitt
 */
@Composable
fun <T> DefaultExposedDropdownMenu(
    field: DropDownTextField<T>,
    labelResId: Int,
    modifier: Modifier = Modifier,
    showClearOption: Boolean = false,
    clearOptionText: String = ""
) {
    DefaultExposedDropdownMenu(
        field = field,
        labelResId = labelResId,
        expanded = field.expanded,
        onExpandedChange = field.onDropDownExpandedChange,
        onMenuDismissRequest = field.onDropDownDismissRequest,
        onItemClick = field.onDataListItemClick,
        items = field.dataListFiltered,
        modifier = modifier,
        showClearOption = showClearOption,
        clearOptionText = clearOptionText
    )
}

/**
 * Um menu suspenso exposto padrão, que é uma alternativa ao [ExposedDropdownMenu] do Material Design.
 *
 * @param T O tipo de valor dos itens do menu.
 * @param field O estado do campo de texto associado.
 * @param labelResId O ID do recurso de string para o rótulo do campo.
 * @param expanded `true` se o menu estiver expandido, `false` caso contrário.
 * @param onExpandedChange Callback para quando o estado de expansão do menu for alterado.
 * @param onMenuDismissRequest Callback para quando o menu for dispensado.
 * @param onItemClick Callback para quando um item do menu for clicado.
 * @param items A lista de [MenuItem] a serem exibidos no menu.
 * @param modifier O [Modifier] a ser aplicado ao [ExposedDropdownMenuBox].
 * @param showClearOption `true` para exibir uma opção para limpar a seleção, `false` caso contrário.
 * @param clearOptionText O texto a ser exibido para a opção de limpar.
 *
 * @author Nikolas Luiz Schmitt
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DefaultExposedDropdownMenu(
    field: ITextField,
    labelResId: Int,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onMenuDismissRequest: () -> Unit,
    onItemClick: (MenuItem<T?>) -> Unit,
    items: List<MenuItem<T?>>,
    modifier: Modifier = Modifier,
    showClearOption: Boolean = false,
    clearOptionText: String = "",
    trailingIconColor: Color = MaterialTheme.colorScheme.onBackground
) {
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = onExpandedChange,
    ) {
        OutlinedTextFieldValidation(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            field = field,
            label = stringResource(labelResId),
            trailingIcon = {
                IconButtonArrowDown(
                    modifier = Modifier
                        .rotate(if (expanded) 180f else 0f),
                    onClick = { },
                    iconColor = trailingIconColor
                )
            },
            readOnly = true
        )

        DropdownMenu(
            modifier = Modifier
                .exposedDropdownSize()
                .fillMaxHeight(fraction = 0.5f),
            expanded = expanded,
            onDismissRequest = onMenuDismissRequest
        ) {
            if (showClearOption) {
                DropdownMenuItem(
                    text = { Text(text = clearOptionText) },
                    onClick = {
                        items.selectValue(null)
                        onItemClick(MenuItem<T?>(clearOptionText, null))
                    }
                )
            }

            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item.label) },
                    onClick = {
                        items.selectValue(item.value)
                        onItemClick(item)
                    }
                )
            }
        }
    }
}

/**
 * Seleciona um valor na lista de [MenuItem], marcando o item correspondente como selecionado.
 *
 * @param T O tipo de valor dos itens.
 * @param value O valor a ser selecionado.
 *
 * @author Nikolas Luiz Schmitt
 */
fun <T> List<MenuItem<T?>>.selectValue(value: T?) {
    forEach { it.selected = false }
    firstOrNull { it.value == value }?.selected = true
}

/**
 * Retorna o rótulo do [MenuItem], ou uma string vazia se o valor for nulo.
 *
 * @param T O tipo de valor do item.
 * @return O rótulo do item, ou uma string vazia.
 *
 * @author Nikolas Luiz Schmitt
 */
fun <T> MenuItem<T?>.getLabelOrEmptyIfNullValue(): String {
    return if (value == null) "" else label
}