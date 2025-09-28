package br.com.android.work.manager.toolkit.workers.coroutine.interfaces

/**
 * Define um contrato para Workers cuja lógica principal deve ser executada dentro de uma transação.
 *
 * Esta interface padroniza a implementação de workers que operam em bancos de dados ou
 * outros sistemas transacionais, garantindo que as operações sejam atômicas.
 *
 * @author Nikolas Luiz Schmitt
 */
interface ITransactionalWorker {
    /**
     * Contém a lógica de negócio do Worker que será executada dentro do escopo da transação
     * fornecida por [getTransactionManager].
     */
    suspend fun onRunWithTransaction()

    /**
     * Fornece o gerenciador de transação.
     *
     * A implementação deste método deve retornar uma função de ordem superior que recebe
     * um bloco `suspend` (a lógica de [onRunWithTransaction]) e o executa dentro de
     * um contexto transacional (ex: `db.withTransaction { ... }`).
     *
     * @return Uma função que executa um bloco de código `suspend` dentro de uma transação.
     */
    fun getTransactionManager(): (block: suspend () -> Unit) -> Unit
}