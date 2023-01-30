package com.android.acromine.data

import android.util.Log
import com.android.acromine.data.pojo.LongFormResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.UnknownHostException

/**
 * Class that handles the usecase regarding the api call for getting the long form
 */

class LongFormUseCase(private val longFormRepository: LongFormRepository) {
    private val TAG: String = javaClass.simpleName
    private var errorCode: String = ""

    /**
     * this method will validate , execute the shortform and handle the error scenarios based on different error types.
     * @param shortForm
     */
    suspend fun getLongFormRetrofit(shortForm: String): Result<List<LongFormResponse>> {
        Log.d(TAG, "getLongFormRetrofit() called shortForm = $shortForm")
        return withContext(Dispatchers.IO) {
            return@withContext try {
                if (isValidShortForm(shortForm)) {
                    val responseData = longFormRepository.getLongFormRetrofit(shortForm)
                    if (responseData.isNotEmpty()) {
                        Result.Success(responseData)
                    } else {
                        Result.Error(IOException(ErrorValue.RESPONSE_IS_EMPTY))
                    }
                } else {
                    Result.Error(IOException(errorCode))
                }
            } catch (exception: UnknownHostException) {
                Result.Error(IOException(ErrorValue.HOST_NO_INTERNET))
            } catch (exception: Throwable) {
                Result.Error(IOException("Exception is $exception"))
            }
        }
    }

    /**
     * Function to check if the entered short form is valid or not
     * @param shortForm shortform
     */
    private fun isValidShortForm(shortForm: String): Boolean {
        Log.d(TAG, "isValidShortForm() called with: shortForm = $shortForm")
        errorCode = ""
        if (shortForm.isEmpty()) {
            errorCode = ErrorValue.INVALID_SHORT_FORM_EMPTY
            return false
        } else {
            if (shortForm.contains("[0-9]".toRegex()) && shortForm.contains("[!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())) {
                errorCode = ErrorValue.INVALID_SHORT_FORM_NUMERIC_SYMBOLS
                return false
            } else if (shortForm.contains("[!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())) {
                errorCode = ErrorValue.INVALID_SHORT_FORM_SYMBOLS
                return false
            } else if (shortForm.contains("[0-9]".toRegex())) {
                errorCode = ErrorValue.INVALID_SHORT_FORM_NUMERIC
                return false
            }
        }
        return true
    }
}