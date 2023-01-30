package com.android.acromine.data

import com.android.acromine.network.ApiInstance
import com.android.acromine.network.BASE_URL

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LongFormRepository {
     suspend fun getLongFormRetrofit(shortForm: String) =
        ApiInstance.getRetrofit(BASE_URL).getLongFormResponse(shortForm)
}