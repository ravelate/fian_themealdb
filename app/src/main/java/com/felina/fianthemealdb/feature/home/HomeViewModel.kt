package com.felina.fianthemealdb.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.felina.fianthemealdb.core.domain.usecase.MealUseCase

class HomeViewModel(mealUseCase: MealUseCase) : ViewModel() {
    val category = mealUseCase.getAllCategory().asLiveData()
    val area = mealUseCase.getAllArea().asLiveData()
}
