package br.com.android.firebase.toolkit.analytics

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent

/**
 * Registra um evento de clique em botão no Firebase Analytics.
 *
 * @param enum Um valor [Enum] que identifica unicamente o botão clicado. O nome do enum
 * será usado como `ITEM_ID`.
 *
 * @author Nikolas Luiz Schmitt
 */
fun FirebaseAnalytics.logButtonClick(enum: Enum<*>) {
    logEvent(EnumAnalyticsEvents.BUTTON_CLICK.name) {
        param(FirebaseAnalytics.Param.ITEM_ID, enum.name)
    }
}

/**
 * Registra um evento de clique em um item de um Bottom Sheet no Firebase Analytics.
 *
 * @param enum Um valor [Enum] que identifica unicamente o item clicado.
 *
 * @author Nikolas Luiz Schmitt
 */
fun FirebaseAnalytics.logBottomSheetItemClick(enum: Enum<*>) {
    logEvent(EnumAnalyticsEvents.BOTTOM_SHEET_ITEM_CLICK.name) {
        param(FirebaseAnalytics.Param.ITEM_ID, enum.name)
    }
}

/**
 * Registra um evento de clique em uma aba (Tab) no Firebase Analytics.
 *
 * @param enum Um valor [Enum] que identifica unicamente a aba clicada.
 *
 * @author Nikolas Luiz Schmitt
 */
fun FirebaseAnalytics.logTabClick(enum: Enum<*>) {
    logEvent(EnumAnalyticsEvents.TAB_CLICK.name) {
        param(FirebaseAnalytics.Param.ITEM_ID, enum.name)
    }
}

/**
 * Registra um evento de rolagem (scroll) para uma nova aba no Firebase Analytics.
 *
 * @param enum Um valor [Enum] que identifica unicamente a aba de destino da rolagem.
 *
 * @author Nikolas Luiz Schmitt
 */
fun FirebaseAnalytics.logTabScroll(enum: Enum<*>) {
    logEvent(EnumAnalyticsEvents.TAB_SCROLL.name) {
        param(FirebaseAnalytics.Param.ITEM_ID, enum.name)
    }
}

/**
 * Registra um evento de clique em um item de uma lista no Firebase Analytics.
 *
 * @param enum Um valor [Enum] que identifica unicamente o item da lista clicado.
 *
 * @author Nikolas Luiz Schmitt
 */
fun FirebaseAnalytics.logListItemClick(enum: Enum<*>) {
    logEvent(EnumAnalyticsEvents.LIST_ITEM_CLICK.name) {
        param(FirebaseAnalytics.Param.ITEM_ID, enum.name)
    }
}