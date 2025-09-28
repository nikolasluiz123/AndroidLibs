package br.com.android.work.manager.toolkit.workers.log

/**
 * Objeto que centraliza as constantes de TAG para logging relacionadas aos workers.
 *
 * Utilizar estas constantes garante consistência nos logs e facilita a filtragem
 * no Logcat.
 *
 * @author Nikolas Luiz Schmitt
 */
object LogConstants {
    /** TAG para workers de importação. */
    const val WORKER_IMPORT = "WORKER_IMPORT"

    /** TAG para workers de exportação. */
    const val WORKER_EXPORT = "WORKER_EXPORT"
}