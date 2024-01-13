package com.felina.fianthemealdb.core.domain.usecase

import com.felina.fianthemealdb.core.domain.repository.IMealRepository

class MealInteractor(private val mealRepository: IMealRepository): MealUseCase {

    override fun getAllCategory() = mealRepository.getAllCategory()
    override fun getAllArea() = mealRepository.getAllArea()
}