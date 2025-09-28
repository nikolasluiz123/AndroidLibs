package br.com.android.firebase.toolkit.crashlytics

import br.com.android.firebase.toolkit.crashlytics.exception.NoLoggingException
import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics

/**
 * Envia um [Throwable] para o Firebase Crashlytics.
 *
 * Esta função de extensão verifica se a exceção é uma instância de [NoLoggingException].
 * Se for, a exceção é ignorada e não é registrada, permitindo um controle fino sobre
 * quais erros devem ser reportados.
 *
 * @receiver O [Throwable] a ser enviado.
 *
 * @see [NoLoggingException]
 * @author Nikolas Luiz Schmitt
 */
fun Throwable.sendToFirebaseCrashlytics() {
    if (this !is NoLoggingException) {
        Firebase.crashlytics.recordException(this)
    }
}