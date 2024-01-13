package com.felina.fianthemealdb.core.data.source.remote

import android.util.Log
import com.felina.fianthemealdb.core.data.source.remote.network.ApiResponse
import com.felina.fianthemealdb.core.data.source.remote.network.ApiService
import com.felina.fianthemealdb.core.data.source.remote.response.AreaItem
import com.felina.fianthemealdb.core.data.source.remote.response.CategoriesItem
import com.felina.fianthemealdb.core.data.source.remote.response.MealResponse
import com.felina.fianthemealdb.core.data.source.remote.response.MealsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllCategory(): Flow<ApiResponse<List<CategoriesItem>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getCategory()
                val dataArray = response.categories
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.categories))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllArea(): Flow<ApiResponse<List<AreaItem>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getArea()
                val dataArray = response.meals
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.meals))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllMeal(name: String, type: String): Flow<ApiResponse<List<MealsItem>>> {
        //get data from remote api
        return flow {
            try {
                if (type == "category"){
                    val response = apiService.getMealByCategory(name)
                    val dataArray = response.meals
                    if (dataArray.isNotEmpty()){
                        emit(ApiResponse.Success(response.meals))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }else if (type == "area"){
                    val response = apiService.getMealByArea(name)
                    val dataArray = response.meals
                    if (dataArray.isNotEmpty()){
                        emit(ApiResponse.Success(response.meals))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}