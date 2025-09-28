package br.com.android.work.manager.toolkit.workers.coroutine.onetime.sync.interfaces

/**
 * Define um contrato para Workers que dependem de um token de autenticação para executar
 * suas tarefas.
 *
 * Esta interface centraliza a lógica de obtenção de um token, que pode envolver a
 * verificação de validade, a renovação (refresh) ou a busca em um armazenamento local.
 *
 * @author Nikolas Luiz Schmitt
 */
interface ITokenAuthWorker {
    /**
     * Deve ser implementado para obter um token de autenticação válido.
     *
     * A implementação é responsável por toda a lógica de validação e/ou renovação do token.
     *
     * @return Uma [String] contendo o token válido, ou `null` se um token válido não puder ser obtido.
     */
    suspend fun getValidTokenOrNull(): String?
}