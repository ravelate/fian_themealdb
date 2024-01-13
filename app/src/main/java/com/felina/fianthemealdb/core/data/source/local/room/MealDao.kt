package com.felina.fianthemealdb.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.felina.fianthemealdb.core.data.source.local.entity.AreaEntity
import com.felina.fianthemealdb.core.data.source.local.entity.CategoryEntity
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
}