package com.uag.augk12.viewmodel

import androidx.lifecycle.ViewModel
import com.uag.augk12.data.repository.BenefitRepository
import com.uag.augk12.data.models.BenefitModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BenefitViewModel(
    private val repository: BenefitRepository
): ViewModel() {
    private val _benefits = MutableStateFlow<List<BenefitModel>>(emptyList())
    val benefits: StateFlow<List<BenefitModel>> = _benefits

    init {
        loadBenefits()
    }

    private fun loadBenefits() {
        _benefits.value = repository.genBenefit()
    }

    fun getBenefitByCode(code: String): BenefitModel? {
        return benefits.value.find { it.code == code }
    }
}