package br.com.android.ui.compose.components.buttons.switchbutton

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import br.com.android.ui.compose.components.R
import br.com.android.ui.compose.components.buttons.switchbutton.state.SwitchButtonField

@Composable
fun BaseSwitchButton(
    field: SwitchButtonField,
    modifier: Modifier = Modifier,
    colors: SwitchColors = getBaseSwitchButtonColors()
) {
    var checked by remember { mutableStateOf(field.checked) }

    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
            field.onCheckedChange(it)
        },
        enabled = field.enabled,
        colors = colors,
        thumbContent = { BaseSwitchButtonIcon(field) },
        modifier = modifier
    )
}

@Composable
fun getBaseSwitchButtonColors(): SwitchColors {
    return SwitchDefaults.colors(
        checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
        checkedTrackColor = MaterialTheme.colorScheme.primary,
        checkedBorderColor = Color.Transparent,
        checkedIconColor = MaterialTheme.colorScheme.primary,
        uncheckedThumbColor = MaterialTheme.colorScheme.primary,
        uncheckedTrackColor = MaterialTheme.colorScheme.onPrimary,
        uncheckedBorderColor = MaterialTheme.colorScheme.primary,
        uncheckedIconColor = MaterialTheme.colorScheme.onPrimary
    )
}

@Composable
fun BaseSwitchButtonIcon(field: SwitchButtonField) {
    if (field.checked) {
        Icon(
            painter = painterResource(R.drawable.ic_switch_button_checked),
            contentDescription = null,
            modifier = Modifier
                .size(SwitchDefaults.IconSize)
        )
    } else {
        Icon(
            painter = painterResource(R.drawable.ic_switch_button_unchecked),
            contentDescription = null,
            modifier = Modifier
                .size(SwitchDefaults.IconSize)
        )
    }
}