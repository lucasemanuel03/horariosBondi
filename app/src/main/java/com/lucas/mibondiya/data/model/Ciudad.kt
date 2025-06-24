package com.lucas.mibondiya.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ciudades")
data class Ciudad(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo
    val nombre: String,
    @ColumnInfo
    val latitud: Double,
    @ColumnInfo
    val longitud: Double,
)