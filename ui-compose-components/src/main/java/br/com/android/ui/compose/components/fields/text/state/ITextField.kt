package br.com.android.ui.compose.components.fields.text.state

interface ITextField {
    val value: String
    val onChange: (String) -> Unit
    val errorMessage: String
}