package br.com.android.work.manager.toolkit.workers.coroutine.periodic.sync

import android.content.Context
import android.util.Log
import androidx.work.WorkerParameters
import br.com.android.work.manager.toolkit.extensions.getRunImportWorker
import br.com.android.work.manager.toolkit.extensions.workerDataStore
import br.com.android.work.manager.toolkit.workers.log.WorkerLogConstants

/**
 * Classe base para Workers periódicos responsáveis pela **importação** de dados.
 *
 * Estende [AbstractSyncPeriodicWorker] e verifica a flag `runImportWorker`
 * do [workerDataStore] antes de cada execução. A lógica de importação é
 * encapsulada com logs de início e fim.
 *
 * @see [AbstractSyncPeriodicWorker]
 *
 * @author Nikolas Luiz Schmitt
 */
abstract class AbstractImportationPeriodicWorker(
    context: Context,
    workerParams: WorkerParameters
): AbstractSyncPeriodicWorker(context, workerParams) {

    final override suspend fun shouldRunWorker(): Boolean {
        return context.workerDataStore.getRunImportWorker()
    }

    final override suspend fun onRunWithTransaction() {
        Log.i(WorkerLogConstants.WORKER_IMPORT, "${"-".repeat(50)} Iniciando Importação ${javaClass.simpleName} ${"-".repeat(50)}")
        onImport()
        Log.i(WorkerLogConstants.WORKER_IMPORT, "${"-".repeat(50)} Finalizando Importação ${javaClass.simpleName} ${"-".repeat(50)}")
    }

    /**
     * Onde a lógica de importação de dados deve ser implementada.
     *
     * Este método será executado dentro do escopo transacional fornecido por [AbstractSyncPeriodicWorker].
     */
    abstract suspend fun onImport()
}