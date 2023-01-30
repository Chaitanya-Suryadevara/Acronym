package com.android.acromine.data

/**
 * this class have different error string constants based on different error scenarios to update user
 */
object ErrorValue {

    const val INVALID_SHORT_FORM_EMPTY: String = "Entered short form is empty"
    const val INVALID_SHORT_FORM_NUMERIC: String = "Short form cannot have numeric values"
    const val INVALID_SHORT_FORM_SYMBOLS: String = "Short form cannot have special characters"
    const val INVALID_SHORT_FORM_NUMERIC_SYMBOLS: String =
        "Short form cannot have numeric nor special characters"

    const val HOST_NO_INTERNET: String = "Please check your internet connection"

    const val RESPONSE_IS_EMPTY: String = "No result found"
}