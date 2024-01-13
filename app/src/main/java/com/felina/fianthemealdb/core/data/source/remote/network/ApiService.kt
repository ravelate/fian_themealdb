package com.felina.fianthemealdb.core.data.source.remote.network

import com.felina.fianthemealdb.core.data.source.remote.response.AreaResponse
import com.felina.fianthemealdb.core.data.source.remote.response.CategoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("categories.php")
    suspend fun getCategory(): CategoryResponse

    @GET("list.php")
    suspend fun getArea(@Query("a") area: String = "list"): AreaResponse

    @GET("list.php")
    suspend fun getMeal(
        @Query("c") meal: String
    ): AreaResponse

}