package com.felina.moviefianapp.core.utils

import com.felina.fianthemealdb.core.data.source.local.entity.AreaEntity
import com.felina.fianthemealdb.core.data.source.local.entity.CategoryEntity
import com.felina.fianthemealdb.core.data.source.local.entity.MealEntity
import com.felina.fianthemealdb.core.data.source.remote.response.AreaItem
import com.felina.fianthemealdb.core.data.source.remote.response.CategoriesItem
import com.felina.fianthemealdb.core.data.source.remote.response.MealsItem
import com.felina.fianthemealdb.core.domain.model.Area
import com.felina.fianthemealdb.core.domain.model.Category
import com.felina.fianthemealdb.core.domain.model.Meal

object DataMapper {
    fun CategorymapResponsesToEntities(input: List<CategoriesItem>): List<CategoryEntity> {
        val CatList = ArrayList<CategoryEntity>()
        input.map {
            val category = CategoryEntity(
                idCategory = it.idCategory,
                strCategory = it.strCategory,
                strCategoryDescription = it.strCategoryDescription,
                strCategoryThumb = it.strCategoryThumb
            )
            CatList.add(category)
        }
        return CatList
    }
    fun CategorymapEntitiesToDomain(input: List<CategoryEntity>): List<Category> =
        input.map {
            Category(
                idCategory = it.idCategory,
                strCategory = it.strCategory,
                strCategoryDescription = it.strCategoryDescription,
                strCategoryThumb = it.strCategoryThumb
            )
        }

    fun AreamapResponsesToEntities(input: List<AreaItem>): List<AreaEntity> {
        val areaList = ArrayList<AreaEntity>()
        input.map {
            val area = AreaEntity(
                strArea = it.strArea
            )
            areaList.add(area)
        }
        return areaList
    }
    fun AreamapEntitiesToDomain(input: List<AreaEntity>): List<Area> =
        input.map {
            Area(
                strArea = it.strArea
            )
        }
    fun MealmapResponsesToEntities(input: List<MealsItem>): List<MealEntity> {
        val mealList = ArrayList<MealEntity>()
        input.map {
            val meal = MealEntity(
                strMealThumb = it.strMealThumb,
                idMeal = it.idMeal,
                strMeal = it.strMeal,
            )
            mealList.add(meal)
        }
        return mealList
    }
    fun MealmapEntitiesToDomain(input: List<MealEntity>): List<Meal> =
        input.map {
            Meal(
                strMealThumb = it.strMealThumb,
                idMeal = it.idMeal,
                strMeal = it.strMeal,
                isFavorite = it.isFavorite
            )
        }
}