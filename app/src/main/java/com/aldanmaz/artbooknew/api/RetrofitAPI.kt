package com.aldanmaz.artbooknew.api

import com.aldanmaz.artbooknew.model.ImageResponse
import com.aldanmaz.artbooknew.util.Util.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {

    @GET("/api/")
    suspend fun imageSearch(
        @Query("q") searchQuery : String,
        @Query("key") apiKey : String = API_KEY
    ) : retrofit2.Response<ImageResponse>
}