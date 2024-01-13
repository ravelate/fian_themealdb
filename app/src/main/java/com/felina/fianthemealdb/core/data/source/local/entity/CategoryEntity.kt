package com.felina.fianthemealdb.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryEntity (
    @ColumnInfo(name = "strCategory")
    val strCategory: String,
    @ColumnInfo(name = "strCategoryDescription")
    val strCategoryDescription: String,
    @PrimaryKey
    @ColumnInfo(name = "idCategory")
    val idCategory: String,
    @ColumnInfo(name = "strCategoryThumb")
    val strCategoryThumb: String
)