package br.com.core.utils.security

/**
 * Define um contrato para classes que realizam o hashing de senhas.
 *
 * Esta interface abstrai a implementação específica do algoritmo de hashing,
 * permitindo que diferentes estratégias de hashing possam ser utilizadas
 * de forma intercambiável no sistema.
 */
interface IPasswordHasher {

    /**
     * Gera um hash seguro a partir de uma senha em texto plano.
     *
     * @param password A senha em texto plano a ser "hasheada".
     * @return Uma [String] contendo o hash seguro da senha,
     * pronta para ser armazenada.
     */
    fun hashPassword(password: String): String
}