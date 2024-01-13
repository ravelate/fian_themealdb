package com.felina.fianthemealdb.core.domain.usecase

import com.felina.fianthemealdb.core.domain.model.Meal
import com.felina.fianthemealdb.core.domain.repository.IMealRepository

class MealInteractor(private val mealRepository: IMealRepository): MealUseCase {

    override fun getAllCategory() = mealRepository.getAllCategory()
    override fun getAllArea() = mealRepository.getAllArea()
    override fun getAllMeal(name: String, type: String) = mealRepository.getAllMeal(name, type)
    override fun getDetailMeal(id: Int) = mealRepository.getDetailMeal(id)
    override fun getSearchMeal(meal: String) = mealRepository.getSearchMeal(meal)
    override fun getFavoriteMeal() = mealRepository.getFavoriteMeal()
    override fun setFavoriteMeal(meal: Meal, state: Boolean) = mealRepository.setFavoriteMeal(meal, state)

}