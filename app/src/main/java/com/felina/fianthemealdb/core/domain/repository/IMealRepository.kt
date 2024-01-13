package com.felina.fianthemealdb.core.domain.repository

import com.felina.fianthemealdb.core.domain.model.Area
import com.felina.fianthemealdb.core.domain.model.Category
import com.felina.fianthemealdb.core.domain.model.Meal
import com.felina.moviefianapp.core.data.Resource
import kotlinx.coroutines.flow.Flow

interface IMealRepository {
    fun getAllCategory(): Flow<Resource<List<Category>>>
    fun getAllArea(): Flow<Resource<List<Area>>>

    fun getAllMeal(name: String, type: String): Flow<Resource<List<Meal>>>
    fun getFavoriteMeal(): Flow<Resource<List<Meal>>>
    fun setFavoriteMeal(meal: Meal, state: Boolean)
}