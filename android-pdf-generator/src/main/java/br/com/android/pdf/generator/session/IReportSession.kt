package br.com.android.pdf.generator.session

import br.com.android.pdf.generator.common.IDrawable
import br.com.android.pdf.generator.common.IPreparable

/**
 * Interface que representa uma sessão dentro do corpo do relatório.
 *
 * As sessões são usadas para agrupar e organizar o conteúdo, geralmente
 * contendo um título e uma série de [br.com.android.pdf.generator.components.IReportComponent].
 *
 * @param FILTER O tipo do objeto de filtro.
 *
 * @author Nikolas Luiz Schmitt
 */
interface IReportSession<FILTER: Any>: IPreparable<FILTER>, IDrawable {

    /**
     * Determina se a sessão deve ser renderizada no relatório.
     *
     * Permite ocultar condicionalmente seções inteiras com base nos parâmetros do filtro
     * ou em dados carregados.
     *
     * @param filter O objeto de filtro do relatório.
     * @return `true` se a sessão deve ser desenhada, `false` caso contrário. Padrão é `true`.
     */
    fun shouldRender(filter: FILTER): Boolean = true
}