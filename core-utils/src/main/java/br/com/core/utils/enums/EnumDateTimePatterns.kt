package br.com.core.utils.enums

/**
 * Enumeração que define padrões de formato para datas e horas.
 *
 * Esta enumeração fornece padrões de formato para formatar datas e horas em strings.
 *
 * @property pattern O padrão de formato para datas e horas.
 * @constructor Cria uma instância da enumeração com o padrão de formato fornecido.
 *
 * @author Nikolas Luiz Schmitt
 */
enum class EnumDateTimePatterns(val pattern: String) {

    /**
     * Padrão de formato para datas no formato "dd/MM/yyyy".
     */
    DATE("dd/MM/yyyy"),

    /**
     * Formato utilizado normalmente para persistência em banco de dados quando deseja optar por algo
     * de melhor visualização em relação a um long. Padrão: "yyyy-MM-dd".
     */
    DATE_SQLITE("yyyy-MM-dd"),

    /**
     * Padrão de formato para datas no formato "ddMMyyyy".
     *
     * Normalmente utilizado para formatar as datas nos campos que
     * possuem um VisualTransformation aplicados pois no retorno do texto
     * ele vem sem as barras da data.
     */
    DATE_ONLY_NUMBERS("ddMMyyyy"),

    /**
     * Padrão de formato para horas no formato "HH:mm".
     */
    TIME("HH:mm"),

    /**
     * Padrão de formato para horas no formato "HHmm".
     */
    TIME_ONLY_NUMBERS("HHmm"),

    /**
     * Padrão de formato para datas e horas no formato "dd/MM/yyyy HH:mm".
     */
    DATE_TIME("dd/MM/yyyy HH:mm"),

    /**
     * Padrão de formato para datas e horas no formato "dd/MM/yy HH:mm". Simplificação de [DATE_TIME]
     * reduzindo dígitos do ano.
     */
    DATE_TIME_SHORT("dd/MM/yy HH:mm"),

    /**
     * Padrão de formato para datas e horas no formato "dd/MM HH:mm". Simplificação de [DATE_TIME]
     * removendo informações do ano.
     */
    DAY_MONTH_DATE_TIME_SHORT("dd/MM HH:mm"),

    /**
     * Padrão de formato para datas no formato "dd/MM". Simplificação de [DATE] removendo o ano.
     */
    DAY_MONTH_DATE("dd/MM"),

    /**
     * Padrão de formato para datas e horas no formato "dd/MM/yyyy HH:mm:ss.SSS". Padrão utilizado para
     * persistência em banco de dados quando deseja optar por algo de melhor visualização em relação a um
     * long.
     */
    DATE_TIME_SQLITE("yyyy-MM-dd HH:mm:ss.SSS"),

    /**
     * Padrão variante de [DATE_TIME] comumente utilizado em nome de arquivos.
     */
    DATE_TIME_FILE_NAME("dd_MM_yyyy_HHmmss"),

    /**
     * Padrão de formatação para visualização do mês por extenso e o ano
     */
    MONTH_YEAR("MMMM 'de' yyyy"),

    /**
     * Variação de [DATE_TIME_FILE_NAME] com algumas informações adicionais para torná-lo mais único.
     */
    BACKUP_DB_FILE_NAME("dd_MM_yyyy_HH_mm_ss_SSS"),
}