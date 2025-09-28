package br.com.android.pdf.generator.common

/**
 * Define um contrato para as partes de um relatório que necessitam de uma etapa de preparação
 * assíncrona antes de serem desenhadas.
 *
 * Esta interface é utilizada para garantir que todos os dados necessários (ex: de um banco de dados
 * ou de uma API) sejam carregados antes que o processo de renderização do PDF comece.
 *
 * @param FILTER O tipo genérico do objeto de filtro, que pode conter parâmetros para
 * carregar os dados necessários.
 *
 * @author Nikolas Luiz Schmitt
 */
interface IPreparable<FILTER: Any> {

    /**
     * Executa todos os processos de preparação necessários para o componente.
     *
     * A implementação padrão é uma função vazia, tornando a preparação opcional para
     * componentes que não precisam carregar dados assincronamente.
     *
     * @param filter O objeto de filtro contendo os parâmetros para a preparação.
     */
    suspend fun prepare(filter: FILTER) = Unit
}