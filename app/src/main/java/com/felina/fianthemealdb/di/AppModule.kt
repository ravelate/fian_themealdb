package com.felina.fianthemealdb.di

import com.felina.fianthemealdb.core.domain.usecase.MealInteractor
import com.felina.fianthemealdb.core.domain.usecase.MealUseCase
import com.felina.fianthemealdb.feature.home.HomeViewModel
import com.felina.fianthemealdb.feature.meal.MealViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MealUseCase> { MealInteractor(get()) }
}
val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { MealViewModel(get()) }
}