package br.com.android.work.manager.toolkit.workers.coroutine.onetime

import android.content.Context
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkerParameters
import br.com.android.work.manager.toolkit.requester.OneTimeWorkerRequester
import br.com.android.work.manager.toolkit.workers.coroutine.AbstractCoroutineWorker

/**
 * Uma classe base abstrata para Workers de execução única ([OneTimeWorkRequest]) que se re-agendam
 * automaticamente após uma execução bem-sucedida.
 *
 * Este padrão é útil para tarefas que precisam rodar em intervalos, mas cuja lógica de
 * agendamento é mais complexa do que a oferecida por um `PeriodicWorkRequest`. Por exemplo,
 * o intervalo pode precisar ser calculado dinamicamente.
 *
 * Ao final de cada execução, a função [scheduleNext] é chamada para enfileirar a
 * próxima instância do mesmo worker.
 *
 * @see [OneTimeWorkerRequester]
 * @author Nikolas Luiz Schmitt
 */
abstract class AbstractOneTimeCoroutineWorker(
    context: Context,
    workerParams: WorkerParameters
) : AbstractCoroutineWorker(context, workerParams) {

    private lateinit var requester: OneTimeWorkerRequester

    /**
     * Onde a lógica de uma única execução do worker deve ser implementada.
     */
    abstract suspend fun onWorkOneTime()

    /**
     * Deve retornar a classe (`.java`) da implementação concreta deste Worker.
     *
     * Este método é necessário para que a classe base possa se re-agendar corretamente.
     * Exemplo de implementação: `return MyWorker::class.java`
     *
     * @return A classe da implementação do Worker.
     */
    abstract fun getClazz(): Class<out AbstractOneTimeCoroutineWorker>

    /**
     * Deve retornar um [OneTimeWorkRequest.Builder] pré-configurado para o próximo agendamento.
     *
     * Aqui podem ser definidas configurações específicas para a próxima execução, como
     * `setInputData`.
     *
     * @return Um builder para a próxima requisição de trabalho.
     */
    abstract fun getOneTimeWorkRequestBuilder(): OneTimeWorkRequest.Builder

    /**
     * O atraso (em minutos) para o próximo agendamento.
     *
     * @return O intervalo em minutos até a próxima execução.
     */
    abstract fun getWorkerDelay(): Long

    final override suspend fun onWork() {
        onWorkOneTime()
        scheduleNext()
    }

    /**
     * Agenda a próxima execução deste worker utilizando um [OneTimeWorkerRequester].
     */
    private fun scheduleNext() {
        if (!this::requester.isInitialized) {
            requester = OneTimeWorkerRequester(
                context = context,
                clazz = getClazz(),
                builder = getOneTimeWorkRequestBuilder(),
                workerDelay = getWorkerDelay()
            )
        }

        requester.enqueue()
    }
}