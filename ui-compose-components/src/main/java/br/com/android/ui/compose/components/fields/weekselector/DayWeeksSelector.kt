package br.com.android.ui.compose.components.fields.weekselector

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.android.ui.compose.components.styles.LabelTextStyle
import br.com.core.utils.extensions.getShortDisplayName
import java.time.DayOfWeek

@Composable
fun DayWeeksSelector(
    selectorField: DayWeeksSelectorField,
    selectedDaysColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            DayOfWeek.entries.forEach { week ->
                DayWeekCell(
                    week = week,
                    isSelected = selectorField.selected.contains(week),
                    selectedColor = selectedDaysColor,
                    onWeekClick = {
                        if (selectorField.selected.contains(it)) {
                            selectorField.selected.remove(it)
                        } else {
                            selectorField.selected.add(it)
                        }

                        selectorField.onSelect(it)
                    }
                )
            }
        }
    }
}

@Composable
private fun DayWeekCell(
    week: DayOfWeek,
    isSelected: Boolean,
    onWeekClick: (DayOfWeek) -> Unit,
    selectedColor: Color
) {
    val colorAnimationDuration = 300

    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) selectedColor else Color.Transparent,
        animationSpec = tween(durationMillis = colorAnimationDuration),
        label = "dayWeekCellBackgroundColor"
    )

    val textColor by animateColorAsState(
        targetValue = if (isSelected) Color.White else MaterialTheme.colorScheme.onBackground,
        animationSpec = tween(durationMillis = colorAnimationDuration),
        label = "dayWeekCellTextColor"
    )

    val interactionSource = remember { MutableInteractionSource() }

    Box(
        Modifier
            .padding(4.dp)
            .size(40.dp)
            .background(color = backgroundColor, shape = CircleShape)
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(bounded = true, radius = 20.dp, color = Color.Gray)
            ) {
                onWeekClick(week)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = week.getShortDisplayName(),
            style = LabelTextStyle,
            color = textColor
        )
    }
}