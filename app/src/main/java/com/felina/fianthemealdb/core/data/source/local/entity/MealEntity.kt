package com.felina.fianthemealdb.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal")
data class MealEntity (
    @ColumnInfo(name = "strMealThumb")
    val strMealThumb: String,
    @PrimaryKey
    @ColumnInfo(name = "idMeal")
    val idMeal: String,
    @ColumnInfo(name = "strMeal")
    val strMeal: String,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,
)