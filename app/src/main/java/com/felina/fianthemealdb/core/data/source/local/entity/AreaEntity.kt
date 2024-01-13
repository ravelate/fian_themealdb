package com.felina.fianthemealdb.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "area")
data class AreaEntity(
    @PrimaryKey
    @ColumnInfo(name = "strArea")
    val strArea: String
)
