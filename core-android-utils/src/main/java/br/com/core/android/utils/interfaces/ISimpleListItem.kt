package br.com.core.android.utils.interfaces

/**
 * Interface que define o contrato para um item de um diálogo de lista simples.
 * Garante que cada item possa fornecer um rótulo de texto.
 *
 * @author Nikolas Luiz Schmitt
 */
interface ISimpleListItem {
    /**
     * Retorna o rótulo de texto a ser exibido para este item na lista.
     */
    fun getLabel(): String
}