package com.felina.fianthemealdb.feature.meal

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.felina.fianthemealdb.core.domain.model.Meal
import com.felina.fianthemealdb.core.domain.usecase.MealUseCase
import com.felina.moviefianapp.core.data.Resource
import kotlinx.coroutines.flow.Flow

class MealViewModel(private val mealUseCase: MealUseCase) : ViewModel() {
    fun getAllMeal(name: String?, type: String?): LiveData<Resource<List<Meal>>> {
        return mealUseCase.getAllMeal(name.toString(),type.toString()).asLiveData()
    }
    val favorite = mealUseCase.getFavoriteMeal().asLiveData()
}