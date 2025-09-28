package br.com.android.firebase.toolkit.authentication

import android.content.Context
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

/**
 * Classe base abstrata para implementar a autenticação com Google via Firebase.
 *
 * Abstrai a complexidade da nova API Credential Manager para obter o Google ID Token
 * e utilizá-lo para autenticar o usuário no Firebase Authentication.
 *
 * @param context O [Context] da aplicação.
 *
 * @author Nikolas Luiz Schmitt
 */
abstract class AbstractFirebaseGoogleAuthenticationService(protected val context: Context) {

    /**
     * Deve retornar o Client ID do servidor, obtido no Console de APIs do Google.
     *
     * Este ID é essencial para a opção `GetSignInWithGoogleOption`.
     *
     * @return O Server Client ID.
     */
    abstract fun getServerClientId(): String

    /**
     * Inicia o fluxo de "Sign in with Google".
     *
     * Utiliza o Credential Manager para solicitar as credenciais do Google e, se bem-sucedido,
     * autentica o usuário no Firebase.
     *
     * @return Um [AuthResult] se a autenticação for bem-sucedida, ou `null` se o usuário
     * cancelar o fluxo ou ocorrer um erro.
     */
    suspend fun signIn(): AuthResult? = withContext(IO) {
        val googleCredential = getGoogleCredential()

        if (googleCredential is CustomCredential && googleCredential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            val token = GoogleIdTokenCredential.createFrom(googleCredential.data)
            val firebaseCredential = GoogleAuthProvider.getCredential(token.idToken, null)

            Firebase.auth.signInWithCredential(firebaseCredential).await()
        } else {
            null
        }
    }

    private suspend fun getGoogleCredential(): Credential? {
        val signInWithGoogleOption = GetSignInWithGoogleOption
            .Builder(serverClientId = getServerClientId())
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(signInWithGoogleOption)
            .build()

        return try {
            val result = CredentialManager.create(context).getCredential(
                request = request,
                context = context
            )

            result.credential
        } catch (ex: GetCredentialCancellationException) {
            null
        }
    }
}