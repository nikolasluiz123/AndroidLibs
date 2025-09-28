package br.com.core.android.compose.utils.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import br.com.core.android.compose.utils.R
import br.com.core.android.utils.media.FileUtils
import br.com.core.android.utils.media.VideoUtils
import java.io.File

fun Context.openCameraVideo(
    cameraVideoLauncher: ManagedActivityResultLauncher<Uri, Boolean>,
    onVideoReady: (Uri, File) -> Unit
) {
    val file = VideoUtils.createVideoFile(this)
    val uri = FileUtils.getUriForFileUsingProvider(this, file)

    onVideoReady(uri, file)
    cameraVideoLauncher.launch(uri)
}

fun Context.openVideoPlayer(filePath: String) {
    val file = File(filePath)
    val uri = FileUtils.getUriForFileUsingProvider(this, file)

    val intent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(uri, "video/*")
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    startActivity(Intent.createChooser(intent, getString(R.string.open_video_player_message)))
}

fun Context.openPDFReader(filePath: String) {
    val file = File(filePath)
    val uri = FileUtils.getUriForFileUsingProvider(this, file)

    val intent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(uri, "application/pdf")
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    startActivity(Intent.createChooser(intent, getString(R.string.open_pdf_reader_message)))
}
