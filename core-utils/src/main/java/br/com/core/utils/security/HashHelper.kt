package br.com.core.utils.security

import java.security.MessageDigest
import java.util.Base64
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

/**
 * Objeto utilitário responsável pela lógica de hashing de senhas.
 *
 * Utiliza o algoritmo PBKDF2 com HMAC-SHA256, que é um padrão da indústria
 * para hashing de senhas, pois é computacionalmente intensivo, o que dificulta
 * ataques de força bruta.
 */
object HashHelper {
    private const val PREFIX = "HASHED_"

    /**
     * Aplica um hash seguro a um valor de [String] (senha).
     *
     * O processo envolve:
     * 1. Gerar um "salt" determinístico a partir da própria senha.
     * 2. Usar PBKDF2 com 10.000 iterações para derivar a chave.
     * 3. Codificar o hash resultante em Base64.
     * 4. Adicionar um prefixo para identificar o valor como hasheado.
     *
     * @param value A [String] em texto plano a ser hasheada.
     * @return O hash seguro em formato Base64 com um prefixo.
     */
    fun applyHash(value: String): String {
        val iterations = 10000
        val keyLength = 256
        val spec = PBEKeySpec(value.toCharArray(), getSalt(value), iterations, keyLength)
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val hash = factory.generateSecret(spec).encoded
        val hashResult = Base64.getEncoder().encodeToString(hash)

        return "$PREFIX$hashResult"
    }

    /**
     * Gera um "salt" de 16 bytes de forma determinística a partir do próprio valor.
     *
     * @param value A [String] a partir da qual o salt será derivado.
     * @return Um [ByteArray] de 16 bytes para ser usado como salt.
     */
    private fun getSalt(value: String): ByteArray {
        val digest = MessageDigest.getInstance("SHA-256")
        return digest.digest(value.toByteArray()).take(16).toByteArray()
    }
}