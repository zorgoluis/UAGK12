package com.uag.augk12.viewmodel

import android.app.DownloadManager
import android.content.Context
import android.content.IntentFilter
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uag.augk12.data.models.DownloadStatus
import com.uag.augk12.receiver.DownloadStatusReceiver
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class DownloadViewModel(
    private val ctx:Context
): ViewModel() {
    private val _status = MutableStateFlow<DownloadStatus?>(null)
    val status: StateFlow<DownloadStatus?> = _status

    fun startDownload(url:String) {
        viewModelScope.launch {
            downloadFilesAsFlow(ctx, url).collect {
                _status.value = it
            }
        }
    }

    fun downloadFilesAsFlow(
        context:Context,
        url: String,
        requestBuilder: (DownloadManager.Request) -> DownloadManager.Request = {it}
    ): Flow<DownloadStatus> = flow {
        val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = requestBuilder(DownloadManager.Request(Uri.parse(url))
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "downloaded_file")
        )

        val id = dm.enqueue(request)
        val receiver = DownloadStatusReceiver(context, id)
        context.registerReceiver(
            receiver,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),
            Context.RECEIVER_EXPORTED
        )

        try {
            emit(receiver.awaitDownloaStatus())
        } finally {
            context.unregisterReceiver(receiver)
        }
    }
}