package com.felina.fianthemealdb.core.data

import com.felina.fianthemealdb.core.data.source.local.LocalDataSource
import com.felina.fianthemealdb.core.data.source.remote.RemoteDataSource
import com.felina.fianthemealdb.core.data.source.remote.network.ApiResponse
import com.felina.fianthemealdb.core.data.source.remote.response.AreaItem
import com.felina.fianthemealdb.core.data.source.remote.response.CategoriesItem
import com.felina.fianthemealdb.core.data.source.remote.response.DetailItem
import com.felina.fianthemealdb.core.data.source.remote.response.MealsItem
import com.felina.fianthemealdb.core.domain.model.Area
import com.felina.fianthemealdb.core.domain.model.Category
import com.felina.fianthemealdb.core.domain.model.Detail
import com.felina.fianthemealdb.core.domain.model.Meal
import com.felina.fianthemealdb.core.domain.repository.IMealRepository
import com.felina.moviefianapp.core.data.NetworkBoundResource
import com.felina.moviefianapp.core.data.Resource
import com.felina.moviefianapp.core.utils.AppExecutors
import com.felina.moviefianapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
                appExecutors.diskIO().execute {
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
                appExecutors.diskIO().execute{
                    localDataSource.insertArea(areaList)
                }
            }
        }.asFlow()

    override fun getAllMeal(name: String, type: String): Flow<Resource<List<Meal>>> =
        object : NetworkBoundResource<List<Meal>, List<MealsItem>>() {
            override fun loadFromDB(): Flow<List<Meal>> {
                return localDataSource.getAllMeal().map {
                    DataMapper.MealmapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Meal>?) = true

            override suspend fun createCall(): Flow<ApiResponse<List<MealsItem>>> =
                remoteDataSource.getAllMeal(name, type)

            override suspend fun saveCallResult(data: List<MealsItem>) {
                val mealList = DataMapper.MealmapResponsesToEntities(data)
                appExecutors.diskIO().execute{
                    localDataSource.insertMeal(mealList)
                }
            }
        }.asFlow()
    override fun getDetailMeal(id: Int): Flow<Resource<List<Detail>>> =
        object : NetworkBoundResource<List<Detail>, List<DetailItem>>() {
            var detail: List<Detail>? = null
            override fun loadFromDB(): Flow<List<Detail>> {
                if (detail != null){
                    return flow{emit(detail!!)}
                }else {
                    return flow { emit(emptyList()) }
                }
            }

            override fun shouldFetch(data: List<Detail>?) = true

            override suspend fun createCall(): Flow<ApiResponse<List<DetailItem>>> =
                remoteDataSource.getDetailMeal(id)

            override suspend fun saveCallResult(data: List<DetailItem>) {
                detail = DataMapper.DetailMapResponseToDomain(data)
            }
        }.asFlow()
    override fun getFavoriteMeal():Flow<Resource<List<Meal>>> =
        object : NetworkBoundResource<List<Meal>, List<MealsItem>>() {
            override fun loadFromDB(): Flow<List<Meal>> {
                return localDataSource.getFavoriteMeal().map {
                    DataMapper.MealmapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Meal>?) = false

            override suspend fun createCall(): Flow<ApiResponse<List<MealsItem>>> =
                remoteDataSource.getAllMeal("Canada", "area")

            override suspend fun saveCallResult(data: List<MealsItem>) {

            }
        }.asFlow()

    override fun setFavoriteMeal(meal: Meal, state: Boolean) {
        val mealEntity = DataMapper.MealmapDomainToEntity(meal)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMeal(mealEntity, state) }
    }
}