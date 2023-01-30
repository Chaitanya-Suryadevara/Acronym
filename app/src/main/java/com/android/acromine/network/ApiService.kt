package com.android.acromine.network

import com.android.acromine.data.pojo.LongFormResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * class for conversion of short name to base url to api
 */
interface ApiService {
    @GET(API_LONG_FORM)
    suspend fun getLongFormResponse(@Query("sf") shortForm: String?): List<LongFormResponse>
}

/***
 *  -> Test Cases
 *  -> File header, Function Header
 *  -> Move the dimens to @dimens
 *  -> Move string ot @string
 */