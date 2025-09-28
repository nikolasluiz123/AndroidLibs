package br.com.android.ui.compose.components.buttons.switchbutton.state

data class SwitchButtonField(
    var checked: Boolean = false,
    val onCheckedChange: (Boolean) -> Unit = { },
    val enabled: Boolean = true
)