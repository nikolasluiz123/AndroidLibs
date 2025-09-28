package br.com.android.ui.compose.components.fields.text.date

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import br.com.android.ui.compose.components.buttons.icons.IconButtonCalendar
import br.com.android.ui.compose.components.dialog.time.BaseDatePickerDialog
import br.com.android.ui.compose.components.fields.text.OutlinedTextFieldValidation
import br.com.android.ui.compose.components.fields.text.date.state.DatePickerTextField
import br.com.android.ui.compose.components.fields.text.date.transformations.DateVisualTransformation

@Composable
fun DatePickerOutlinedTextFieldValidation(
    field: DatePickerTextField,
    fieldLabel: String,
    modifier: Modifier = Modifier,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    imeAction: ImeAction = ImeAction.Next
) {
    OutlinedTextFieldValidation(
        field = field,
        label = fieldLabel,
        modifier = modifier,
        trailingIcon = {
            IconButtonCalendar(
                iconColor = MaterialTheme.colorScheme.secondary
            ) { field.onDatePickerOpenChange(true) }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = imeAction
        ),
        keyboardActions = keyboardActions,
        visualTransformation = DateVisualTransformation(),
        maxLength = 8
    )

    if (field.datePickerOpen) {
        BaseDatePickerDialog(
            onDismissRequest = field.onDatePickerDismiss,
            onConfirm = field.onDateChange,
            onCancel = field.onDatePickerDismiss
        )
    }
}