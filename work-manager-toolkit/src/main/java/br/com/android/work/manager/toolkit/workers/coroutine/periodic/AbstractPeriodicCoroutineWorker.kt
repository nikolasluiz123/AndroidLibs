package br.com.android.work.manager.toolkit.workers.coroutine.periodic

import android.content.Context
import androidx.work.WorkerParameters
import br.com.android.work.manager.toolkit.workers.coroutine.AbstractCoroutineWorker

/**
 * Uma classe base abstrata para Workers periódicos que estende [AbstractCoroutineWorker].
 *
 * Esta classe simplifica a implementação de workers que são agendados para rodar
 * repetidamente, fornecendo um único método abstrato [onWorkPeriodic] para a
 * implementação da lógica de execução.
 *
 * @see [AbstractCoroutineWorker]
 *
 * @author Nikolas Luiz Schmitt
 */
abstract class AbstractPeriodicCoroutineWorker(
    context: Context,
    workerParams: WorkerParameters
) : AbstractCoroutineWorker(context, workerParams) {

    /**
     * Onde a lógica de uma única execução do worker periódico deve ser implementada.
     */
    abstract suspend fun onWorkPeriodic()

    final override suspend fun onWork() {
        onWorkPeriodic()
    }
}