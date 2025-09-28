package br.com.android.firebase.toolkit.firestore.service

import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import kotlin.to

/**
 * Classe base abstrata para serviços que interagem com o Cloud Firestore.
 *
 * @property db A instância do [com.google.firebase.firestore.FirebaseFirestore].
 *
 * @author Nikolas Luiz Schmitt
 */
abstract class FirestoreService {

    protected val db = Firebase.firestore

    /**
     * Retorna o timestamp atual do servidor do Firestore.
     *
     * Este método é útil para padronizar todos os campos de data e hora com um valor
     * consistente e confiável, independente do relógio do dispositivo do cliente.
     *
     * @return O timestamp do servidor em milissegundos.
     */
    protected suspend fun getServerTime(): Long {
        val dummyDocRef = db.collection("serverTime").document("timestamp")

        val data = mapOf("timestamp" to FieldValue.serverTimestamp())
        dummyDocRef.set(data).await()

        val snapshot = dummyDocRef.get().await()
        val serverTimestamp = snapshot.getTimestamp("timestamp")?.seconds!! * 1000

        return serverTimestamp
    }
}