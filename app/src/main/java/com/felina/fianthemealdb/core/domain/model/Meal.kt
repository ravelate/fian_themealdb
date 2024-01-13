package com.felina.fianthemealdb.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meal(
    val strMealThumb: String,
    val idMeal: String,
    val strMeal: String,
    val isFavorite: Boolean
): Parcelable
