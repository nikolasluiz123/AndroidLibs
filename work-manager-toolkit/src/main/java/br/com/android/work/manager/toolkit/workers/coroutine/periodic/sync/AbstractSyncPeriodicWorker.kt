package br.com.android.work.manager.toolkit.workers.coroutine.periodic.sync

import android.content.Context
import androidx.work.WorkerParameters
import br.com.android.work.manager.toolkit.workers.coroutine.interfaces.IConditionalRunWorker
import br.com.android.work.manager.toolkit.workers.coroutine.interfaces.IThrowableWorker
import br.com.android.work.manager.toolkit.workers.coroutine.interfaces.ITransactionalWorker
import br.com.android.work.manager.toolkit.workers.coroutine.periodic.AbstractPeriodicCoroutineWorker

/**
 * Uma classe base que combina as funcionalidades de vários padrões para workers
 * de execução periódica.
 *
 * Esta classe é ideal para workers que:
 * 1.  Executam periodicamente ([AbstractPeriodicCoroutineWorker]).
 * 2.  Executam sua lógica principal dentro de uma transação ([ITransactionalWorker]).
 * 3.  Podem ser condicionalmente pulados em uma execução ([IConditionalRunWorker]).
 * 4.  Possuem um comportamento customizado ao lidar com erros antes de uma nova tentativa ([IThrowableWorker]).
 *
 * A lógica é orquestrada em [onWorkPeriodic], que primeiro verifica a condição de
 * execução e, se satisfeita, invoca a lógica transacional.
 *
 * @author Nikolas Luiz Schmitt
 */
abstract class AbstractSyncPeriodicWorker(
    context: Context,
    workerParams: WorkerParameters
): AbstractPeriodicCoroutineWorker(context, workerParams), IThrowableWorker, IConditionalRunWorker, ITransactionalWorker {

    final override suspend fun onWorkPeriodic() {
        if (shouldRunWorker()) {
            getTransactionManager().invoke(::onRunWithTransaction)
        }
    }

    final override suspend fun onError(e: Exception): Result {
        onBeforeErrorRetry(e)
        return Result.retry()
    }
}