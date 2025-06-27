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
    suspend fun insert(vararg horarios : Horario)

    @Delete()
    suspend fun delete(horario : Horario)

    //SELECTS
    @Query("SELECT * FROM horarios")
    suspend fun getAll() : List<Horario>

    @Query("SELECT * FROM horarios WHERE id=:id")
    suspend fun getById(id :Int) : Horario

    @Query("SELECT * FROM horarios WHERE ciudad_inicio_id=:ciudadInicioId")
    suspend fun getAllHorariosCiudadInicio(ciudadInicioId : Int) : List<Horario>

    @Query("SELECT * FROM horarios WHERE ciudad_fin_id=:ciudadFinId")
    suspend fun getAllHorariosCiudadFin(ciudadFinId : Int) : List<Horario>

    @Query("SELECT EXISTS(SELECT 1 FROM horarios LIMIT 1)")
    suspend fun hasAnyHorario(): Boolean

}
