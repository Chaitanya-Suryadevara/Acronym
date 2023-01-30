package com.android.acromine.ui.longform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.acromine.data.LongFormRepository
import com.android.acromine.data.LongFormUseCase

/**
 * ViewModel provider factory to instantiate LongFormViewModel.
 * Required given LongFormViewModel has a non-empty constructor
 */
class LongFormViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LongFormViewModel::class.java)) {
            return LongFormViewModel(
                longFormUseCase = LongFormUseCase(
                    longFormRepository = LongFormRepository()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}