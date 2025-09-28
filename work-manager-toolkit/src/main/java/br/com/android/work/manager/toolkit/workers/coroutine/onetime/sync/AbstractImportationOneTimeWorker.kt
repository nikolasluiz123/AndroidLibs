package br.com.android.work.manager.toolkit.workers.coroutine.onetime.sync

import android.content.Context
import android.util.Log
import androidx.work.WorkerParameters
import br.com.android.work.manager.toolkit.extensions.getRunImportWorker
import br.com.android.work.manager.toolkit.extensions.workerDataStore
import br.com.android.work.manager.toolkit.workers.log.LogConstants

/**
 * Classe base para Workers de execução única responsáveis pela **importação** de dados.
 *
 * Estende [AbstractSyncOneTimeWorker] e verifica a flag `runImportWorker` do [workerDataStore]
 * antes de executar. A lógica de importação é encapsulada dentro de logs de início e fim.
 *
 * @see [AbstractSyncOneTimeWorker]
 *
 * @author Nikolas Luiz Schmitt
 */
abstract class AbstractImportationOneTimeWorker(
    context: Context,
    workerParams: WorkerParameters
) : AbstractSyncOneTimeWorker(context, workerParams) {

    final override suspend fun shouldRunWorker(): Boolean {
        return context.workerDataStore.getRunImportWorker()
    }

    override suspend fun onRunWithTransaction() {
        Log.i(LogConstants.WORKER_IMPORT, "${"-".repeat(50)} Iniciando Importação ${javaClass.simpleName} ${"-".repeat(50)}")
        onImport()
        Log.i(LogConstants.WORKER_IMPORT, "${"-".repeat(50)} Finalizando Importação ${javaClass.simpleName} ${"-".repeat(50)}")
    }

    /**
     * Onde a lógica de importação de dados deve ser implementada.
     *
     * Este método será executado dentro do escopo transacional fornecido por [AbstractSyncOneTimeWorker].
     */
    abstract suspend fun onImport()
}