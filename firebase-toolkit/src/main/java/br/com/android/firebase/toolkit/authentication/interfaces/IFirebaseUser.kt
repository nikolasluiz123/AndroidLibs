package br.com.android.firebase.toolkit.authentication.interfaces

/**
 * Define um contrato para objetos que contêm informações de credenciais de um usuário
 * (e-mail e senha).
 *
 * Utilizado para passar dados de usuário de forma agnóstica para os serviços de autenticação.
 *
 * @property email O e-mail do usuário.
 * @property password A senha do usuário.
 *
 * @author Nikolas Luiz Schmitt
 */
interface IFirebaseUser {
    val email: String?
    val password: String?
}