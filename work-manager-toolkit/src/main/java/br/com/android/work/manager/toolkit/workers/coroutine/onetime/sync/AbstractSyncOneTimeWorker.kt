package br.com.android.work.manager.toolkit.workers.coroutine.onetime.sync

import android.content.Context
import androidx.work.WorkerParameters
import br.com.android.work.manager.toolkit.workers.coroutine.interfaces.IConditionalRunWorker
import br.com.android.work.manager.toolkit.workers.coroutine.interfaces.IThrowableWorker
import br.com.android.work.manager.toolkit.workers.coroutine.interfaces.ITransactionalWorker
import br.com.android.work.manager.toolkit.workers.coroutine.onetime.AbstractOneTimeCoroutineWorker

/**
 * Uma classe base que combina as funcionalidades de vários padrões para workers de
 * execução única.
 *
 * Esta classe é ideal para workers que:
 * 1.  Executam uma única vez e se re-agendam ([AbstractOneTimeCoroutineWorker]).
 * 2.  Executam sua lógica principal dentro de uma transação ([ITransactionalWorker]).
 * 3.  Podem ser condicionalmente pulados ([IConditionalRunWorker]).
 * 4.  Possuem um comportamento customizado ao lidar com erros antes de uma nova tentativa ([IThrowableWorker]).
 *
 * A lógica principal é orquestrada em [onWorkOneTime], que verifica a condição de
 * execução e, se satisfeita, invoca a lógica transacional.
 *
 * @author Nikolas Luiz Schmitt
 */
abstract class AbstractSyncOneTimeWorker(
    context: Context,
    workerParams: WorkerParameters
) : AbstractOneTimeCoroutineWorker(context, workerParams), IThrowableWorker, IConditionalRunWorker, ITransactionalWorker {

    final override suspend fun onWorkOneTime() {
        if (shouldRunWorker()) {
            getTransactionManager().invoke(::onRunWithTransaction)
        }
    }

    final override suspend fun onError(e: Exception): Result {
        onBeforeErrorRetry(e)
        return Result.retry()
    }

}