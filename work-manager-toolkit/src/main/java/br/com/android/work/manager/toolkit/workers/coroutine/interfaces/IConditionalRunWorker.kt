package br.com.android.work.manager.toolkit.workers.coroutine.interfaces

/**
 * Define um contrato para Workers que devem ser executados apenas sob certas condições.
 *
 * Esta interface permite que um Worker encapsule a lógica que determina se sua execução
 * deve ou não prosseguir.
 *
 * @author Nikolas Luiz Schmitt
 */
interface IConditionalRunWorker {
    /**
     * Verifica se o Worker deve executar sua lógica principal.
     *
     * @return `true` se o trabalho deve ser executado, `false` caso contrário.
     */
    suspend fun shouldRunWorker(): Boolean
}