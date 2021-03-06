package com.example.jobplanettest.restapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


// https://jpassets.jobplanet.co.kr/mobile-config/test_data.json
interface JobPlanetAPI {
    @GET("mobile-config/test_data.json")
    fun getTestData(
        @Query("display") display: Int? = null,
        @Query("start") start: Int? = null
    ): Call<JobPlanetCompanyInfoList>
}