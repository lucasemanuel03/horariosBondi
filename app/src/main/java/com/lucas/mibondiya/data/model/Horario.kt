package com.lucas.mibondiya.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "horarios", foreignKeys = [
    ForeignKey(
        entity = Empresa::class,
        parentColumns = ["id"],
        childColumns = ["empresa_id"],
        onDelete = ForeignKey.CASCADE ),

    ForeignKey(
        entity = Ciudad::class,
        parentColumns = ["id"],
        childColumns = ["ciudad_inicio_id"],
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = Ciudad::class,
        parentColumns = ["id"],
        childColumns = ["ciudad_fin_id"],
        onDelete = ForeignKey.CASCADE
    )
])

data class Horario(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo
    var horaSalida: String,
    @ColumnInfo
    var horaLlegada: String,
    @ColumnInfo
    var empresa_id: Int,
    @ColumnInfo
    var ciudad_inicio_id: Int,
    @ColumnInfo
    var ciudad_fin_id: Int,
    @ColumnInfo
    var seAnuncia: String,
    @ColumnInfo
    var notas: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam pretium blandit felis. Mauris viverra sed est eu lacinia volutpat."
)