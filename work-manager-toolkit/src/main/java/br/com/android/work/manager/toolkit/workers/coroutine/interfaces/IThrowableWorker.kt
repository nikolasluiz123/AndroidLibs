package br.com.android.work.manager.toolkit.workers.coroutine.interfaces

/**
 * Define um contrato para Workers que precisam executar uma ação específica antes de
 * uma nova tentativa (`retry`) ser agendada após um erro.
 *
 * É útil para realizar limpeza, logar informações adicionais ou reverter um estado
 * parcial antes que o [androidx.work.WorkManager] tente executar o trabalho novamente.
 *
 * @author Nikolas Luiz Schmitt
 */
interface IThrowableWorker {
    /**
     * Callback executado imediatamente antes de o worker retornar `Result.retry()`.
     *
     * A implementação padrão é vazia.
     *
     * @param e A exceção que causou a falha.
     */
    fun onBeforeErrorRetry(e: Exception) = Unit
}