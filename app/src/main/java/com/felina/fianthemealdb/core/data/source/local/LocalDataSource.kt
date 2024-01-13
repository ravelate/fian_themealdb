package com.felina.fianthemealdb.core.data.source.local

import com.felina.fianthemealdb.core.data.source.local.entity.AreaEntity
import com.felina.fianthemealdb.core.data.source.local.entity.CategoryEntity
import com.felina.fianthemealdb.core.data.source.local.entity.MealEntity
import com.felina.fianthemealdb.core.data.source.local.room.MealDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mealDao: MealDao) {

    fun getAllCategory(): Flow<List<CategoryEntity>> = mealDao.getAllCategory()
    fun insertCategory(categoryList: List<CategoryEntity>) = mealDao.insertCategory(categoryList)

    fun getAllArea(): Flow<List<AreaEntity>> = mealDao.getAllArea()
    fun insertArea(areaList: List<AreaEntity>) = mealDao.insertArea(areaList)

    fun getAllMeal(): Flow<List<MealEntity>> = mealDao.getAllMeal()
    fun getFavoriteMeal(): Flow<List<MealEntity>> = mealDao.getAllFavoriteMeal()

    fun insertMeal(tourismList: List<MealEntity>) = mealDao.insertMeal(tourismList)
    fun setFavoriteMeal(meal: MealEntity, newState: Boolean) {
        meal.isFavorite = newState
        mealDao.updateFavoriteMeal(meal)
    }

}