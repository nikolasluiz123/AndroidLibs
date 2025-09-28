package br.com.android.ui.compose.components.buttons.icons

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.com.android.ui.compose.components.R

@Composable
fun IconButtonCalendar(
    modifier: Modifier = Modifier,
    iconColor: Color = MaterialTheme.colorScheme.onPrimary,
    enabled: Boolean = true,
    contentDescriptionResId: Int? = R.string.label_calendar,
    onClick: () -> Unit = { }
) {
    BaseIconButton(
        modifier = modifier,
        resId = R.drawable.ic_calendar_24dp,
        iconColor = iconColor,
        enabled = enabled,
        contentDescriptionResId = contentDescriptionResId,
        onClick = onClick
    )
}