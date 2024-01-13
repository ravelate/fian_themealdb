package com.felina.fianthemealdb.feature.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.felina.fianthemealdb.core.domain.model.Detail
import com.felina.fianthemealdb.core.domain.usecase.MealUseCase
import com.felina.moviefianapp.core.data.Resource

class SearchViewModel(private val mealUseCase: MealUseCase) : ViewModel() {
    fun getSearchMeal(meal: String): LiveData<Resource<List<Detail>>> {
        return mealUseCase.getSearchMeal(meal).asLiveData()
    }
}