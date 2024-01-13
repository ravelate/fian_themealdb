package com.felina.fianthemealdb.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.felina.fianthemealdb.core.domain.model.Detail
import com.felina.fianthemealdb.core.domain.model.Meal
import com.felina.fianthemealdb.core.domain.usecase.MealUseCase
import com.felina.moviefianapp.core.data.Resource

class DetailViewModel(val mealUseCase: MealUseCase) : ViewModel() {
    fun setFavoriteMeal(meal: Meal, newStatus:Boolean) =
        mealUseCase.setFavoriteMeal(meal, newStatus)

    fun getDetailMeal(id: Int): LiveData<Resource<List<Detail>>> {
        return mealUseCase.getDetailMeal(id).asLiveData()
    }
}