package br.com.android.room.toolkit.model.interfaces

import android.content.Context

interface IEnumDomain {
    fun  getLabel(context: Context): String? = null

    fun getPluralLabel(context: Context, quantity: Int): String? = null
}