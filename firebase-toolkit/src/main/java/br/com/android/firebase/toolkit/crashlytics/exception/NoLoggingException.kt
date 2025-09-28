package br.com.android.firebase.toolkit.crashlytics.exception

/**
 * Uma exceção especial que, quando capturada e enviada para a extensão
 * `sendToFirebaseCrashlytics`, **não** será registrada no Firebase Crashlytics.
 *
 * Útil para representar erros esperados ou controlados que não devem poluir
 * os relatórios de crash (ex: erros de validação do usuário).
 *
 * @param message A mensagem da exceção.
 * @param cause A causa original da exceção, se houver.
 *
 * @see [br.com.android.firebase.toolkit.crashlytics.sendToFirebaseCrashlytics]
 * @author Nikolas Luiz Schmitt
 */
class NoLoggingException(message: String, cause: Throwable? = null): Exception(message, cause)