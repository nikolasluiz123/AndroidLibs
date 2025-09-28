package br.com.android.work.manager.toolkit.requester

import android.content.Context
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import br.com.android.work.manager.toolkit.workers.coroutine.AbstractCoroutineWorker
import java.util.concurrent.TimeUnit

/**
 * Encapsula a lógica para criar e enfileirar um [OneTimeWorkRequest] único.
 *
 * Esta classe simplifica o agendamento de workers de execução única, configurando
 * de forma consistente as restrições de rede e a política de backoff para novas tentativas.
 *
 * @param context O [Context] da aplicação.
 * @param clazz A classe do [AbstractCoroutineWorker] a ser enfileirado.
 * @param builder O [OneTimeWorkRequest.Builder] para a requisição.
 * @param workerDelay O atraso inicial para a execução do worker, em minutos.
 *
 * @author Nikolas Luiz Schmitt
 */
class OneTimeWorkerRequester(
    private val context: Context,
    private val clazz: Class<out AbstractCoroutineWorker>,
    builder: OneTimeWorkRequest.Builder,
    workerDelay: Long
) {

    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    private val request = builder
        .setConstraints(constraints)
        .setInitialDelay(workerDelay, TimeUnit.MINUTES)
        .setBackoffCriteria(BackoffPolicy.LINEAR, workerDelay, TimeUnit.MINUTES)
        .build()

    /**
     * Enfileira o trabalho como um trabalho único, usando o nome da classe como `uniqueWorkName`.
     *
     * A política `APPEND_OR_REPLACE` é utilizada. Se um trabalho com o mesmo nome estiver
     * pendente ou em execução, ele será cancelado e o novo trabalho o substituirá. Se não houver
     * um trabalho com o mesmo nome, o novo será adicionado à fila.
     */
    fun enqueue() {
        WorkManager.getInstance(context).enqueueUniqueWork(clazz.simpleName, ExistingWorkPolicy.APPEND_OR_REPLACE, request)
    }
}