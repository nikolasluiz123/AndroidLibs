package br.com.android.firebase.toolkit.analytics

/**
 * Enum que define os nomes dos eventos de analytics padronizados na aplicação.
 *
 * A utilização deste enum garante a consistência na nomenclatura dos eventos
 * registrados no Firebase Analytics.
 *
 * @author Nikolas Luiz Schmitt
 */
enum class EnumAnalyticsEvents {
    BUTTON_CLICK,
    BOTTOM_SHEET_ITEM_CLICK,
    TAB_CLICK,
    TAB_SCROLL,
    LIST_ITEM_CLICK
}