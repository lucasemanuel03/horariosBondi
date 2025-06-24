package com.lucas.mibondiya.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucas.mibondiya.data.model.Horario

@Dao
interface HorarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHorario(vararg horarios : Horario)

    @Delete()
    fun delete(horario : Horario)

    //SELECTS
    @Query("SELECT * FROM horarios")
    fun getAll() : List<Horario>

    @Query("SELECT * FROM horarios WHERE id=:id")
    fun getById(id :Int) : Horario

    @Query("SELECT * FROM horarios WHERE ciudad_inicio_id=:ciudadInicioId")
    fun getAllHorariosCiudadInicio(ciudadInicioId : Int) : List<Horario>

    @Query("SELECT * FROM horarios WHERE ciudad_fin_id=:ciudadFinId")
    fun getAllHorariosCiudadFin(ciudadFinId : Int) : List<Horario>

}