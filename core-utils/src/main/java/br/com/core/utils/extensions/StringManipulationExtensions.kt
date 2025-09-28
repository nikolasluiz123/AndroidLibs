package br.com.core.utils.extensions

import java.text.DecimalFormat
import java.text.Normalizer

/**
 * Converte uma [String] em um valor [Double] utilizando as configurações de Locale do dispositivo.
 *
 * @receiver A string contendo o valor decimal a ser convertido.
 * @return O [Double] convertido ou `null` se a conversão falhar.
 * @author Nikolas Luiz Schmitt
 */
fun String.parseDouble(): Double? {
    return try {
        DecimalFormat.getInstance().parse(this)?.toDouble()
    } catch (_: Exception) {
        null
    }
}

/**
 * Converte de forma segura uma [String] para [Int], retornando `null` em caso de falha.
 *
 * @receiver A string a ser convertida.
 * @return O valor [Int] ou `null` se a string não for um número válido.
 */
fun String.toIntOrNull(): Int? {
    return try {
        this.toInt()
    } catch (e: NumberFormatException) {
        null
    }
}

/**
 * Limpa uma [String] recebida como parâmetro de navegação, removendo chaves `{}`.
 * Converte a string "null" para um valor `null` real.
 *
 * @receiver A string de parâmetro a ser tratada. Pode ser nula.
 * @return A string tratada ou `null`.
 * @author Nikolas Luiz Schmitt
 */
fun String?.navParamToString(): String? {
    val value = this?.replace("}", "")?.replace("{", "")
    return if (value == "null") null else value
}

/**
 * Converte um [Any] para [String], retornando uma string vazia se o objeto for nulo.
 *
 * @receiver O objeto a ser convertido.
 * @return A representação em [String] do objeto ou `""` se for nulo.
 */
fun Any?.toStringOrEmpty(): String = this?.toString() ?: ""

/**
 * Verifica se alguma das palavras de busca (`search`) está contida no texto principal.
 * A comparação é feita em minúsculas e sem acentos.
 *
 * @receiver O texto onde a busca será realizada.
 * @param search O texto contendo as palavras a serem buscadas.
 * @return `true` se alguma palavra de `search` for encontrada, `false` caso contrário.
 */
fun String?.searchWordsInText(search: String?): Boolean {
    if (this == null || search == null) {
        return false
    }

    val textNormalized = this.unAccent().lowercase()
    val searchNormalized = search.unAccent().lowercase()
    val words = searchNormalized.trim().split("\\s+".toRegex())

    return words.any { it in textNormalized }
}

/**
 * Remove a acentuação de uma [String].
 *
 * @receiver A string a ser normalizada.
 * @return A string sem acentos, ou a string original se a normalização falhar.
 */
fun String.unAccent(): String {
    val normalizedString = Normalizer.normalize(this, Normalizer.Form.NFD)
    return normalizedString.replace("[^\\p{ASCII}]".toRegex(), "")
}