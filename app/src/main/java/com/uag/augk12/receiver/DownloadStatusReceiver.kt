package com.uag.augk12.receiver

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.uag.augk12.data.models.DownloadStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeoutOrNull
import java.io.File

class DownloadStatusReceiver(
    private val context:Context,
    private val downloadId: Long
): BroadcastReceiver() {
    private var downloadStatus: DownloadStatus? = null

    override fun onReceive(ctx: Context?, intent: Intent?) {
        val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val query = DownloadManager.Query().setFilterById(downloadId)

        dm.query(query)?.use { cursor ->
            if(cursor.moveToFirst()) {
                val status = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS))
                val uri = cursor.getString(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_LOCAL_URI))
                downloadStatus = if(status == DownloadManager.STATUS_SUCCESSFUL) {
                    val file = File(Uri.parse(uri).path)
                    DownloadStatus.Success(file)
                } else {
                    DownloadStatus.Failed(Exception("Descarga fallida con estatus: $status"))
                }
            }
        }
    }

    suspend fun awaitDownloaStatus(): DownloadStatus {
        return withTimeoutOrNull(180_000L) {
            while(downloadStatus === null) {
                delay(100)
            }
            downloadStatus!!
        } ?: DownloadStatus.Canceled
    }
}