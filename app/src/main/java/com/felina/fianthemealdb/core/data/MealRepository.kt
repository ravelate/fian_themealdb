package com.felina.fianthemealdb.core.data

import com.felina.fianthemealdb.core.data.source.local.LocalDataSource
import com.felina.fianthemealdb.core.data.source.remote.RemoteDataSource
import com.felina.fianthemealdb.core.data.source.remote.network.ApiResponse
import com.felina.fianthemealdb.core.data.source.remote.response.AreaItem
import com.felina.fianthemealdb.core.data.source.remote.response.CategoriesItem
import com.felina.fianthemealdb.core.domain.model.Area
import com.felina.fianthemealdb.core.domain.model.Category
import com.felina.fianthemealdb.core.domain.repository.IMealRepository
import com.felina.moviefianapp.core.data.NetworkBoundResource
import com.felina.moviefianapp.core.data.Resource
import com.felina.moviefianapp.core.utils.AppExecutors
import com.felina.moviefianapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MealRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMealRepository {

    override fun getAllCategory(): Flow<Resource<List<Category>>> =
        object : NetworkBoundResource<List<Category>, List<CategoriesItem>>() {
            override fun loadFromDB(): Flow<List<Category>> {
                return localDataSource.getAllCategory().map {
                    DataMapper.CategorymapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Category>?) = true

            override suspend fun createCall(): Flow<ApiResponse<List<CategoriesItem>>> =
                remoteDataSource.getAllCategory()

            override suspend fun saveCallResult(data: List<CategoriesItem>) {
                val categoryList = DataMapper.CategorymapResponsesToEntities(data)
                appExecutors.networkIO().execute {
                    localDataSource.insertCategory(categoryList)
                }
            }
        }.asFlow()

    override fun getAllArea(): Flow<Resource<List<Area>>> =
        object : NetworkBoundResource<List<Area>, List<AreaItem>>() {
            override fun loadFromDB(): Flow<List<Area>> {
                return localDataSource.getAllArea().map {
                    DataMapper.AreamapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Area>?) = true

            override suspend fun createCall(): Flow<ApiResponse<List<AreaItem>>> =
                remoteDataSource.getAllArea()

            override suspend fun saveCallResult(data: List<AreaItem>) {
                val areaList = DataMapper.AreamapResponsesToEntities(data)
                appExecutors.networkIO().execute{
                    localDataSource.insertArea(areaList)
                }
            }
        }.asFlow()
}