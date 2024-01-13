package com.felina.fianthemealdb.core.data.source.remote.network

import com.felina.fianthemealdb.core.data.source.remote.response.AreaResponse
import com.felina.fianthemealdb.core.data.source.remote.response.CategoryResponse
import com.felina.fianthemealdb.core.data.source.remote.response.DetailResponse
import com.felina.fianthemealdb.core.data.source.remote.response.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("categories.php")
    suspend fun getCategory(): CategoryResponse

    @GET("list.php")
    suspend fun getArea(@Query("a") area: String = "list"): AreaResponse

    @GET("filter.php")
    suspend fun getMealByCategory(
        @Query("c") meal: String
    ): MealResponse
    @GET("filter.php")
    suspend fun getMealByArea(
        @Query("a") meal: String
    ): MealResponse

    @GET("lookup.php")
    suspend fun getDetailMeal(
        @Query("i") id: Int
    ): DetailResponse

}