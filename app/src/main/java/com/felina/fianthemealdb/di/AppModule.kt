package com.felina.fianthemealdb.di

import com.felina.fianthemealdb.core.domain.usecase.MealInteractor
import com.felina.fianthemealdb.core.domain.usecase.MealUseCase
import com.felina.fianthemealdb.feature.detail.DetailViewModel
import com.felina.fianthemealdb.feature.home.HomeViewModel
import com.felina.fianthemealdb.feature.meal.MealViewModel
import com.felina.fianthemealdb.feature.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MealUseCase> { MealInteractor(get()) }
}
val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { MealViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}