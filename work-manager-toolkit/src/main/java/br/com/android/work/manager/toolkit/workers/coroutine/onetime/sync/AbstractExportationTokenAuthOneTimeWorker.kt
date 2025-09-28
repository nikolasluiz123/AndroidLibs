package br.com.android.work.manager.toolkit.workers.coroutine.onetime.sync

import android.content.Context
import android.util.Log
import androidx.work.WorkerParameters
import br.com.android.work.manager.toolkit.extensions.getRunExportWorker
import br.com.android.work.manager.toolkit.extensions.workerDataStore
import br.com.android.work.manager.toolkit.workers.coroutine.onetime.sync.interfaces.ITokenAuthWorker
import br.com.android.work.manager.toolkit.workers.log.LogConstants

/**
 * Classe base para Workers de execução única responsáveis por **exportação** de dados
 * que necessitam de um token de autenticação para se comunicar com um serviço.
 *
 * Combina as funcionalidades de [AbstractSyncOneTimeWorker] e [ITokenAuthWorker],
 * garantindo que a lógica de exportação só seja executada se um token válido for obtido
 * e se a flag de execução de exportação estiver ativa.
 *
 * @see [ITokenAuthWorker]
 * @see [AbstractSyncOneTimeWorker]
 *
 * @author Nikolas Luiz Schmitt
 */
abstract class AbstractExportationTokenAuthOneTimeWorker(
    context: Context,
    workerParams: WorkerParameters
) : AbstractSyncOneTimeWorker(context, workerParams), ITokenAuthWorker {

    /**
     * Onde a lógica de exportação deve ser implementada.
     *
     * Este método só será chamado se um token de autenticação válido for obtido.
     *
     * @param serviceToken O token de autenticação válido.
     */
    abstract suspend fun onExport(serviceToken: String)

    final override suspend fun shouldRunWorker(): Boolean {
        return context.workerDataStore.getRunExportWorker()
    }

    final override suspend fun onRunWithTransaction() {
        getValidTokenOrNull()?.let { serviceToken ->
            Log.i(LogConstants.WORKER_EXPORT, "${"-".repeat(50)} Iniciando Exportação ${javaClass.simpleName} ${"-".repeat(50)}")
            onExport(serviceToken)
            Log.i(LogConstants.WORKER_EXPORT, "${"-".repeat(50)} Finalizando Exportação ${javaClass.simpleName} ${"-".repeat(50)}")
        }
    }
}