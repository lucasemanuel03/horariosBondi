package com.lucas.mibondiya.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.lucas.mibondiya.data.model.HorarioCompleto
import kotlinx.coroutines.flow.Flow

@Dao
interface HorarioCompletoDao {

    @Query("""
        SELECT h.id, h.horaSalida, h.horaLlegada, 
               e.nombre AS empresaNombre, e.id_image AS empresaImagenId,
               ci.nombre AS ciudadInicioNombre,
               cf.nombre AS ciudadFinNombre,
               h.seAnuncia,
               h.notas
        FROM horarios h
        INNER JOIN empresas e ON h.empresa_id = e.id
        INNER JOIN ciudades ci ON h.ciudad_inicio_id = ci.id
        INNER JOIN ciudades cf ON h.ciudad_fin_id = cf.id
    """)
    fun getHorariosCompletos(): Flow<List<HorarioCompleto>>

    @Query("""
        SELECT h.id, h.horaSalida, h.horaLlegada, 
               e.nombre AS empresaNombre, e.id_image AS empresaImagenId,
               ci.nombre AS ciudadInicioNombre,
               cf.nombre AS ciudadFinNombre,
               h.seAnuncia,
               h.notas
        FROM horarios h
        INNER JOIN empresas e ON h.empresa_id = e.id
        INNER JOIN ciudades ci ON h.ciudad_inicio_id = ci.id
        INNER JOIN ciudades cf ON h.ciudad_fin_id = cf.id
        WHERE h.id = :horarioId
    """)
    suspend fun getById(horarioId: Int): HorarioCompleto?

    @Query("""
        SELECT h.id, h.horaSalida, h.horaLlegada, 
               e.nombre AS empresaNombre, e.id_image AS empresaImagenId,
               ci.nombre AS ciudadInicioNombre,
               cf.nombre AS ciudadFinNombre,
               h.seAnuncia,
               h.notas
        FROM horarios h
        INNER JOIN empresas e ON h.empresa_id = e.id
        INNER JOIN ciudades ci ON h.ciudad_inicio_id = ci.id
        INNER JOIN ciudades cf ON h.ciudad_fin_id = cf.id
        WHERE cf.id = :ciudadFinId
    """)
    fun getAllByIdCiudadFin(ciudadFinId: Int): Flow<List<HorarioCompleto>>

    @Query("SELECT COUNT(*) FROM horarios")
    suspend fun countHorarios(): Int

    @Query("""
    SELECT h.id, h.horaSalida, h.horaLlegada, 
           e.nombre AS empresaNombre, e.id_image AS empresaImagenId,
           ci.nombre AS ciudadInicioNombre,
           cf.nombre AS ciudadFinNombre,
           h.seAnuncia,
           h.notas
    FROM horarios h
    INNER JOIN empresas e ON h.empresa_id = e.id
    INNER JOIN ciudades ci ON h.ciudad_inicio_id = ci.id
    INNER JOIN ciudades cf ON h.ciudad_fin_id = cf.id
    WHERE ci.id = :ciudadOrigenId
""")
    fun getByCiudadOrigen(ciudadOrigenId: Int): Flow<List<HorarioCompleto>>

}


