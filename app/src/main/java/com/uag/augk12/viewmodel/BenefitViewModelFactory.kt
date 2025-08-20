package com.uag.augk12.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uag.augk12.data.repository.BenefitRepository

class BenefitViewModelFactory(
    private val repository: BenefitRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BenefitViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BenefitViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}