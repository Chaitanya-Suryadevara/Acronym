package com.android.acromine.ui.longform

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.android.acromine.data.LongFormUseCase
import com.android.acromine.data.Result
import kotlinx.coroutines.Dispatchers

/**
 * Monitoring of live data from the response on the button click.
 * the shortform is send through here to api handling class.
 * @param longFormUseCase all buisiness logic is implemented here based on the input
 */
class LongFormViewModel(private val longFormUseCase: LongFormUseCase) : ViewModel() {

    fun getLongFormRetrofit(shortForm: String) = liveData(Dispatchers.IO) {
        Log.d(javaClass.simpleName, "getLongFormRetrofit() called shortForm = $shortForm")
        emit(Result.Loading(0))
        emit(longFormUseCase.getLongFormRetrofit(shortForm))
    }
}