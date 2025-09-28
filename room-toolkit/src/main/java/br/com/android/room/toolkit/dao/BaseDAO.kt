package br.com.android.room.toolkit.dao

import java.util.StringJoiner

/**
 * Classe base para todos os DAOs da aplicação.
 *
 * Fornece funcionalidades comuns e constantes que podem ser reutilizadas
 * pelas subclasses.
 *
 * @author Nikolas Luiz Schmitt
 */
abstract class BaseDAO {

    /**
     * Constrói uma cláusula `IN (...)` parametrizada para uma query SQL.
     *
     * Exemplo de uso: `... WHERE id IN ` + `concatElementsForIn(...)`
     *
     * @param elements A lista de valores a serem incluídos na cláusula IN.
     * @param params A lista mutável onde os parâmetros da query serão adicionados.
     */
    protected fun StringJoiner.concatElementsForIn(elements: List<Any>, params: MutableList<Any>) {
        add("(")

        elements.forEachIndexed { index, element ->
            if (elements.size > 1 && elements.lastIndex != index) add("?,") else add("?")
            params.add(element)
        }

        add(")")
    }

    companion object {
        const val QR_NL = "\r\n"
    }
}