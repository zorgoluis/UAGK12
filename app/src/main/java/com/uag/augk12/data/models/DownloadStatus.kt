package com.uag.augk12.data.models

import java.io.File

sealed class DownloadStatus {
    data class Success(val file:File): DownloadStatus()
    data class Failed(val error: Exception): DownloadStatus()
    object Canceled: DownloadStatus()
}