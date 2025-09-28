package br.com.android.firebase.toolkit.firestore.document

import br.com.core.utils.gson.extensions.defaultGSon
import com.google.common.reflect.TypeToken
import com.google.gson.GsonBuilder

/**
 * Classe base abstrata para objetos que representam documentos no Firestore.
 *
 * Fornece um método utilitário [toMap] para converter a instância do objeto
 * em um [Map], que é o formato necessário para salvar ou atualizar dados no Firestore.
 *
 * @author Nikolas Luiz Schmitt
 */
abstract class FirestoreDocument {

    /**
     * Converte o objeto atual em um mapa de `String` para `Any?`.
     *
     * Utiliza a biblioteca GSon para serializar o objeto para JSON e, em seguida,
     * deserializá-lo para um mapa.
     *
     * @return Um [Map] representando as propriedades do objeto.
     */
    fun toMap(): Map<String, Any?> {
        val gson = GsonBuilder().defaultGSon()
        val json = gson.toJson(this)
        return gson.fromJson(json, object : TypeToken<Map<String, Any?>>() {}.type)
    }
}