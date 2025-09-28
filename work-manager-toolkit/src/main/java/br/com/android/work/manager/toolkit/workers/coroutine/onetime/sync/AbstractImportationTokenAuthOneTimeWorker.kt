package br.com.android.work.manager.toolkit.workers.coroutine.onetime.sync

import android.content.Context
import androidx.work.WorkerParameters
import br.com.android.work.manager.toolkit.workers.coroutine.onetime.sync.interfaces.ITokenAuthWorker
import kotlin.let
import kotlin.text.isNullOrEmpty

/**
 * Classe base para Workers de execução única responsáveis pela **importação** de dados
 * que necessitam de um token de autenticação.
 *
 * Esta classe estende [AbstractImportationOneTimeWorker] e implementa [ITokenAuthWorker].
 * Ela garante que a lógica de importação ([onImport]) só seja executada se um token válido for
 * obtido. Além disso, toda a execução transacional é pulada se um token não puder
 * ser recuperado.
 *
 * @see [ITokenAuthWorker]
 * @see [AbstractImportationOneTimeWorker]
 *
 * @author Nikolas Luiz Schmitt
 */
abstract class AbstractImportationTokenAuthOneTimeWorker(
    context: Context,
    workerParams: WorkerParameters
) : AbstractImportationOneTimeWorker(context, workerParams), ITokenAuthWorker {

    /**
     * Onde a lógica de importação que depende de autenticação deve ser implementada.
     *
     * Este método só será invocado se [getValidTokenOrNull] retornar um token não nulo.
     *
     * @param serviceToken O token de autenticação válido obtido.
     */
    abstract suspend fun onImport(serviceToken: String)

    final override suspend fun onImport() {
        getValidTokenOrNull()?.let { serviceToken ->
            onImport(serviceToken)
        }
    }

    final override suspend fun onRunWithTransaction() {
        if (getValidTokenOrNull().isNullOrEmpty().not()) {
            super.onRunWithTransaction()
        }
    }
}