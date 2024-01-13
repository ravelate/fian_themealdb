package com.felina.fianthemealdb.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.felina.fianthemealdb.core.data.source.local.entity.AreaEntity
import com.felina.fianthemealdb.core.data.source.local.entity.CategoryEntity
import com.felina.fianthemealdb.core.data.source.local.entity.MealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Query("SELECT * FROM category")
    fun getAllCategory(): Flow<List<CategoryEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(movie: List<CategoryEntity>)

    @Query("SELECT * FROM area")
    fun getAllArea(): Flow<List<AreaEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArea(movie: List<AreaEntity>)
    @Query("SELECT * FROM meal")
    fun getAllMeal(): Flow<List<MealEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeal(movie: List<MealEntity>)
    @Query("SELECT * FROM meal where isFavorite = 1")
    fun getAllFavoriteMeal(): Flow<List<MealEntity>>
    @Update
    fun updateFavoriteMeal(meal: MealEntity)
}