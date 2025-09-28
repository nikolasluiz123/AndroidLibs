package br.com.android.ui.compose.components.buttons.switchbutton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import br.com.android.ui.compose.components.buttons.switchbutton.state.SwitchButtonField
import br.com.android.ui.compose.components.styles.LabelTextStyle

@Composable
fun HorizontalLabeledSwitchButton(
    field: SwitchButtonField,
    label: String,
    modifier: Modifier = Modifier,
    labelTextStyle: TextStyle = LabelTextStyle
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BaseSwitchButton(field)
        Text(
            text = label,
            style = labelTextStyle,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}