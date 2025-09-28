package br.com.android.ui.compose.components.fields.weekselector

import androidx.compose.runtime.mutableStateListOf
import java.time.DayOfWeek

data class DayWeeksSelectorField(
    val selected: MutableList<DayOfWeek> = mutableStateListOf(),
    val onSelect: (DayOfWeek) -> Unit = { }
)