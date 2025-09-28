package br.com.android.firebase.toolkit.storage

import android.content.Context
import br.com.android.firebase.toolkit.R
import br.com.core.android.utils.media.FileUtils
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.tasks.await
import java.io.File
import kotlin.collections.forEach
import kotlin.collections.map
import kotlin.collections.zip
import kotlin.text.substringAfterLast

/**
 * Classe base abstrata para serviços que interagem com o Firebase Cloud Storage.
 *
 * Oferece uma estratégia otimizada para download de múltiplos arquivos:
 * - **Download Sequencial:** Para um número pequeno de arquivos, eles são baixados um após o outro.
 * - **Download Paralelo:** Para um grande número de arquivos, os downloads são feitos em paralelo,
 * com um limite de concorrência para não sobrecarregar a rede, controlado por um [Semaphore].
 *
 * @param context O [Context] da aplicação.
 *
 * @author Nikolas Luiz Schmitt
 */
abstract class AbstractStorageBucketService(protected val context: Context) {

    /**
     * O número máximo de downloads que podem ser executados em paralelo.
     */
    abstract fun getMaximumParallelDownloads(): Int

    /**
     * O número máximo de arquivos para usar a estratégia de download sequencial.
     * Acima deste valor, a estratégia de download paralelo será utilizada.
     */
    abstract fun getMaximumSequentialDownloads(): Int

    /**
     * Baixa uma lista de arquivos de um bucket do Storage, utilizando a estratégia
     * de download mais apropriada (sequencial ou paralela).
     *
     * @param storageBucket O nome do bucket no Firebase Storage.
     * @param urls A lista de URLs de download dos arquivos.
     * @param files A lista de objetos [File] de destino. A ordem deve corresponder à lista de URLs.
     * @throws IllegalArgumentException se o número de URLs e arquivos for diferente.
     * @throws Exception se ocorrer um erro durante o download; nesse caso, todos os arquivos
     * já baixados nesta operação serão excluídos.
     */
    suspend fun downloadAllByUrl(storageBucket: String, urls: List<String>, files: List<File>) {
        require(urls.size == files.size) { context.getString(R.string.storage_bucket_service_batch_download_invalid_params) }

        if (urls.size <= getMaximumSequentialDownloads()) {
            downloadAllSequentially(storageBucket, urls, files)
        } else {
            downloadAllParallel(storageBucket, urls, files)
        }
    }

    private suspend fun downloadAllSequentially(storageBucket: String, urls: List<String>, files: List<File>) {
        try {
            urls.zip(files).forEach { (url, file) ->
                downloadByUrl(storageBucket, url, file)
            }
        } catch (e: Exception) {
            FileUtils.deleteFiles(files)
            throw e
        }
    }

    private suspend fun downloadAllParallel(storageBucket: String, urls: List<String>, files: List<File>) {
        val semaphore = Semaphore(getMaximumParallelDownloads())

        try {
            coroutineScope {
                urls.zip(files).map { (url, file) ->
                    async {
                        semaphore.withPermit {
                            downloadByUrl(storageBucket, url, file)
                        }
                    }
                }.awaitAll()
            }
        } catch (e: Exception) {
            FileUtils.deleteFiles(files)
            throw e
        }
    }

    /**
     * Baixa um único arquivo de um bucket do Storage.
     *
     * @param storageBucket O nome do bucket.
     * @param url A URL completa de download do arquivo. O nome do arquivo será extraído da URL.
     * @param file O [File] de destino onde o conteúdo será salvo.
     */
    suspend fun downloadByUrl(storageBucket: String, url: String, file: File) {
        val fullBucketName = "$STORAGE_PREFIX$storageBucket"

        Firebase.storage(fullBucketName)
            .getReference(url.substringAfterLast("/"))
            .getFile(file)
            .await()
    }

    companion object {
        private const val STORAGE_PREFIX = "gs://"
    }
}