package br.com.android.work.manager.toolkit.workers.coroutine

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Uma classe base abstrata para [CoroutineWorker] que fornece uma estrutura robusta
 * para execução de tarefas em segundo plano.
 *
 * ### Funcionalidades Principais:
 * - **Execução em I/O Thread:** Garante que todo o trabalho seja executado no `Dispatchers.IO`,
 * liberando a thread principal.
 * - **Tratamento de Exceções:** Centraliza a captura de exceções, delegando o tratamento
 * para o método [onError], que deve ser implementado pela classe filha.
 * - **Tempo Limite de Retentativas:** Implementa um mecanismo de tempo limite para evitar
 * que um worker com falhas recorrentes seja re-executado indefinidamente.
 *
 * As classes filhas devem implementar [onWork] com a lógica principal, [onError] para
 * tratar falhas, e [getMaxRetryTimeMillis] para definir o tempo máximo de retentativas.
 *
 * @param context O [Context] da aplicação.
 * @param workerParams Parâmetros de configuração para o Worker.
 *
 * @author Nikolas Luiz Schmitt
 */
abstract class AbstractCoroutineWorker(
    protected val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    /**
     * Callback invocado quando uma exceção não tratada ocorre durante a execução de [onWork].
     *
     * A implementação deve decidir o que fazer com o erro, como logar em uma ferramenta de
     * analytics ou decidir se o trabalho deve ser tentado novamente (`Result.retry()`) ou
     * falhar (`Result.failure()`).
     *
     * @param e A [Exception] que foi capturada.
     * @return O [Result] do worker após o tratamento do erro.
     */
    abstract suspend fun onError(e: Exception): Result

    /**
     * O método principal onde a lógica do trabalho em segundo plano deve ser implementada.
     * Este método já é executado dentro de um bloco `try-catch` e no `Dispatchers.IO`.
     */
    abstract suspend fun onWork()

    /**
     * Define o tempo máximo (em milissegundos) que este worker deve tentar ser re-executado
     * a partir de sua primeira tentativa.
     *
     * Se o tempo decorrido desde a primeira execução exceder este valor, o worker
     * resultará em `Result.failure()` automaticamente, prevenindo um loop infinito de retentativas.
     *
     * @return O tempo máximo de re-tentativa em milissegundos.
     */
    abstract fun getMaxRetryTimeMillis(): Long

    final override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val startTime = inputData.getLong("startTime", System.currentTimeMillis())
        val elapsed = System.currentTimeMillis() - startTime

        // Previne re-tentativas após o tempo limite definido
        if (elapsed > getMaxRetryTimeMillis()) {
            return@withContext Result.failure()
        }

        try {
            onWork()
            Result.success()
        } catch (e: Exception) {
            Log.e(COROUTINE_WORKER_TAG, e.message, e)
            onError(e)
        }
    }

    companion object {
        const val COROUTINE_WORKER_TAG = "CoroutineWorker"
    }
}