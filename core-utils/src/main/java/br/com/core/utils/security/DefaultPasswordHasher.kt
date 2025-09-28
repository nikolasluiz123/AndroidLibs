package br.com.core.utils.security

/**
 * Implementação padrão da interface [IPasswordHasher].
 *
 * Delega a lógica de hashing para o [HashHelper], que utiliza um algoritmo
 * robusto para gerar o hash da senha.
 */
class DefaultPasswordHasher : IPasswordHasher {

    /**
     * Gera um hash seguro para a senha fornecida.
     *
     * @param password A senha em texto plano.
     * @return A [String] do hash da senha.
     */
    override fun hashPassword(password: String): String {
        return HashHelper.applyHash(password)
    }
}