package br.com.android.pdf.generator.components

import br.com.android.pdf.generator.common.IDrawable
import br.com.android.pdf.generator.common.IPreparable

/**
 * Interface de marcação para representar um Componente de Relatório.
 *
 * No contexto desta biblioteca, um componente é um elemento visual reutilizável que pode
 * ser adicionado a uma [IReportSession]. Exemplos incluem tabelas, grades de layout,
 * ou campos de texto simples.
 *
 * @param FILTER O tipo do objeto de filtro.
 *
 * @author Nikolas Luiz Schmitt
 */
interface IReportComponent<FILTER: Any>: IPreparable<FILTER>, IDrawable