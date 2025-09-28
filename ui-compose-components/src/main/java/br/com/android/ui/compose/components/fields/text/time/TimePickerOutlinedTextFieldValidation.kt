package br.com.android.ui.compose.components.fields.text.time

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import br.com.android.ui.compose.components.buttons.icons.IconButtonTime
import br.com.android.ui.compose.components.dialog.time.BaseTimePickerDialog
import br.com.android.ui.compose.components.fields.text.OutlinedTextFieldValidation
import br.com.android.ui.compose.components.fields.text.time.state.TimePickerTextField
import br.com.android.ui.compose.components.fields.text.time.transformations.TimeVisualTransformation
import br.com.core.utils.enums.EnumDateTimePatterns
import br.com.core.utils.extensions.parseToLocalTime

@Composable
fun TimePickerOutlinedTextFieldValidation(
    field: TimePickerTextField,
    fieldLabel: String,
    timePickerTitle: String,
    modifier: Modifier = Modifier,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    imeAction: ImeAction = ImeAction.Next
) {
    OutlinedTextFieldValidation(
        field = field,
        label = fieldLabel,
        modifier = modifier,
        trailingIcon = {
            IconButtonTime(
                iconColor = MaterialTheme.colorScheme.secondary
            ) { field.onTimePickerOpenChange(true) }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = imeAction
        ),
        keyboardActions = keyboardActions,
        visualTransformation = TimeVisualTransformation(),
        maxLength = 4
    )

    if (field.timePickerOpen) {
        BaseTimePickerDialog(
            title = timePickerTitle,
            value = field.value.parseToLocalTime(EnumDateTimePatterns.TIME_ONLY_NUMBERS),
            onConfirm = field.onTimeChange,
            onDismiss = field.onTimeDismiss
        )
    }
}