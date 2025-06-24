package com.lucas.mibondiya.data.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class HorarioMock(
    @PrimaryKey val id: Int,
    @ColumnInfo var horaSalida: String,
    @ColumnInfo var horaLlegada: String,
    @ColumnInfo var empresa: Empresa,
    @ColumnInfo var ciudadInicio: Ciudad,
    @ColumnInfo var ciudadFin: Ciudad,
    @ColumnInfo var seAnuncia: String = ciudadFin.nombre,
    @ColumnInfo var notas: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam pretium blandit felis. Mauris viverra sed est eu lacinia volutpat."
)