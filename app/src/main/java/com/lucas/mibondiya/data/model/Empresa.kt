package com.lucas.mibondiya.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "empresas")
data class Empresa(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo
    val nombre: String,
    @ColumnInfo(name = "id_image")
    val idImage: Int,
)