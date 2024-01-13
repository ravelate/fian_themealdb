package com.felina.fianthemealdb.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.felina.fianthemealdb.core.data.source.local.entity.AreaEntity
import com.felina.fianthemealdb.core.data.source.local.entity.CategoryEntity
import com.felina.fianthemealdb.core.data.source.local.entity.MealEntity

@Database(entities = [CategoryEntity::class, AreaEntity::class, MealEntity::class], version = 1, exportSchema = false)
abstract class MealDatabase: RoomDatabase() {
    abstract fun mealDao(): MealDao
}