package br.com.core.android.utils.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * Verifica se o dispositivo possui uma conexão de internet ativa.
 *
 * Requer a permissão `android.permission.ACCESS_NETWORK_STATE`.
 *
 * @receiver O [Context] da aplicação.
 * @return `true` se houver conexão com a internet, `false` caso contrário.
 */
@SuppressLint("MissingPermission")
fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}