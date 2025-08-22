package com.uag.augk12.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DownloadViewModelFactory(
    private val context: Context
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DownloadViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DownloadViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}