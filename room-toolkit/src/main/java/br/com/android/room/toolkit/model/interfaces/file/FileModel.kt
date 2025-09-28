package br.com.android.room.toolkit.model.interfaces.file

/**
 * Define um contrato para entidades que representam ou estão associadas a um arquivo físico.
 *
 * @property kbSize O tamanho do arquivo em kilobytes, se aplicável.
 * @property filePath O caminho local para o arquivo.
 *
 * @author Nikolas Luiz Schmitt
 */
interface FileModel {
    var kbSize: Long?
    var filePath: String?
}