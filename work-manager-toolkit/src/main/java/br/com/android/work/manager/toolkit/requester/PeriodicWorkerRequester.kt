package br.com.android.work.manager.toolkit.requester

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import br.com.android.work.manager.toolkit.workers.coroutine.AbstractCoroutineWorker
import java.util.concurrent.TimeUnit

/**
 * Encapsula a lógica para criar e enfileirar um [PeriodicWorkRequest] único.
 *
 * Simplifica o agendamento de workers periódicos, aplicando restrições de rede
 * e um atraso inicial de forma padronizada.
 *
 * @param context O [Context] da aplicação.
 * @param clazz A classe do [AbstractCoroutineWorker] a ser enfileirado.
 * @param builder O [PeriodicWorkRequest.Builder] para a requisição.
 * @param workerDelay O atraso inicial para a primeira execução do worker, em minutos.
 *
 * @author Nikolas Luiz Schmitt
 */
class PeriodicWorkerRequester(
    private val context: Context,
    private val clazz: Class<out AbstractCoroutineWorker>,
    builder: PeriodicWorkRequest.Builder,
    workerDelay: Long = MIN_PERIODIC_WORKER_DELAY_MINS
) {

    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    private val request = builder
        .setConstraints(constraints)
        .setInitialDelay(workerDelay, TimeUnit.MINUTES)
        .build()

    /**
     * Enfileira o trabalho como um trabalho periódico único.
     *
     * A política `KEEP` garante que, se um trabalho com o mesmo nome já estiver
     * agendado, a nova requisição será ignorada, evitando duplicações.
     */
    fun enqueue() {
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            uniqueWorkName = clazz.simpleName,
            existingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.KEEP,
            request = request
        )
    }

    companion object {
        /**
         * O atraso mínimo permitido para workers periódicos, conforme definido pelo WorkManager (15 minutos).
         */
        const val MIN_PERIODIC_WORKER_DELAY_MINS = 15L
    }
}