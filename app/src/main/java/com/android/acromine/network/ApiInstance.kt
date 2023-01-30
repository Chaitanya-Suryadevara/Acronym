package com.android.acromine.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * The api call based on the search input is called from this class
 * the short term will be converted to based url and executed here
 */
class ApiInstance {

    companion object {

        fun getRetrofit(baseUrl: String): ApiService {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}