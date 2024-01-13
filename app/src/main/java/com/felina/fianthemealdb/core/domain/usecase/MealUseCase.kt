package com.felina.fianthemealdb.core.domain.usecase

import com.felina.fianthemealdb.core.domain.model.Area
import com.felina.fianthemealdb.core.domain.model.Category
import com.felina.moviefianapp.core.data.Resource
import kotlinx.coroutines.flow.Flow

interface MealUseCase {
    fun getAllCategory(): Flow<Resource<List<Category>>>
    fun getAllArea(): Flow<Resource<List<Area>>>
}