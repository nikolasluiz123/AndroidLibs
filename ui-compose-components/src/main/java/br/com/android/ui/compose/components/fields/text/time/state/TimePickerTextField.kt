package br.com.android.ui.compose.components.fields.text.time.state

import br.com.android.ui.compose.components.fields.text.state.ITextField
import java.time.LocalTime

data class TimePickerTextField(
    val timePickerOpen: Boolean = false,
    val onTimePickerOpenChange: (Boolean) -> Unit = { },
    val onTimeChange: (LocalTime) -> Unit = { },
    val onTimeDismiss: () -> Unit = { },
    override val value: String = "",
    override val onChange: (String) -> Unit = { },
    override val errorMessage: String = ""
): ITextField