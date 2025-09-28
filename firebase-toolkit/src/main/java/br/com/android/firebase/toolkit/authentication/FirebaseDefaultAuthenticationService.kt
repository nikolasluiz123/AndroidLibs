package br.com.android.firebase.toolkit.authentication

import android.content.Context
import br.com.android.firebase.toolkit.R
import br.com.android.firebase.toolkit.authentication.interfaces.IFirebaseUser
import br.com.core.android.utils.extensions.isNetworkAvailable
import com.google.firebase.Firebase
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

/**
 * Serviço que encapsula as operações padrão de autenticação com e-mail e senha
 * do Firebase Authentication.
 *
 * @author Nikolas Luiz Schmitt
 */
class FirebaseDefaultAuthenticationService {

    /**
     * Autentica um usuário com e-mail e senha.
     *
     * @param email O e-mail do usuário.
     * @param password A senha do usuário.
     * @return [AuthResult] em caso de sucesso, ou `null` se ocorrer um erro de rede.
     */
    suspend fun authenticate(email: String, password: String): AuthResult? = withContext(IO) {
        try {
            Firebase.auth.signInWithEmailAndPassword(email, password).await()
        } catch (ex: FirebaseNetworkException) {
            null
        }
    }

    /**
     * Registra um novo usuário com e-mail e senha.
     *
     * @param email O e-mail do novo usuário.
     * @param password A senha do novo usuário.
     * @return [AuthResult] em caso de sucesso, ou `null` se ocorrer um erro de rede.
     */
    suspend fun register(email: String, password: String): AuthResult? = withContext(IO) {
        try {
            Firebase.auth.createUserWithEmailAndPassword(email, password).await()
        } catch (ex: FirebaseNetworkException) {
            null
        }
    }

    /**
     * Atualiza o e-mail e a senha do usuário atualmente logado.
     *
     * Lida com o caso de [FirebaseAuthInvalidUserException] que exige reautenticação,
     * realizando o logout e um novo login automaticamente.
     *
     * @param context O [Context] da aplicação.
     * @param user Um objeto que implementa [IFirebaseUser] com as novas informações.
     * @throws FirebaseNetworkException se a reautenticação for necessária e não houver rede.
     */
    suspend fun updateUserInfos(context: Context, user: IFirebaseUser): Unit = withContext(IO) {
        Firebase.auth.currentUser?.let { firebaseUser ->
            try {
                user.email?.let { firebaseUser.verifyBeforeUpdateEmail(it).await() }
                user.password?.let { firebaseUser.updatePassword(it).await() }
            } catch (_: FirebaseAuthInvalidUserException) {
                if (!context.isNetworkAvailable()) {
                    throw FirebaseNetworkException(context.getString(R.string.validation_msg_require_reauthentication))
                }

                logout()
                authenticate(user.email!!, user.password!!)
            }
        }
    }

    /**
     * Realiza o logout do usuário atual.
     */
    fun logout() {
        Firebase.auth.signOut()
    }
}