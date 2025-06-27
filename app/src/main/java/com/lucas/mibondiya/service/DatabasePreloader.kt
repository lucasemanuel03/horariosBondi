package com.lucas.mibondiya.service

import android.util.Log
import com.lucas.mibondiya.R
import com.lucas.mibondiya.data.dao.CiudadDao
import com.lucas.mibondiya.data.dao.EmpresaDao
import com.lucas.mibondiya.data.dao.HorarioDao
import com.lucas.mibondiya.data.model.Ciudad
import com.lucas.mibondiya.data.model.Empresa
import com.lucas.mibondiya.data.model.Horario
import javax.inject.Inject

class DatabasePreloader @Inject constructor(
    private val ciudadDao: CiudadDao,
    private val empresaDao: EmpresaDao,
    private val horarioDao: HorarioDao
) {
    suspend fun preloadDataIfNeeded() {
        Log.d("DB_PRELOAD", "Ejecutando preloadDataIfNeeded")
        val hasHorarios = horarioDao.hasAnyHorario()
        val horariosActuales = horarioDao.getAll()
        Log.d("DB_PRELOAD", "¿Ya hay horarios? -> $hasHorarios")
        Log.d("DB_DATA_ACTUAL", horariosActuales.toString())
        if (!hasHorarios) {
            Log.d("DB_PRELOAD", "No hay horarios. Insertando datos iniciales...")
            ciudadDao.insert(Ciudad(1, "Jesús María", 0.0, 0.0))
            ciudadDao.insert(Ciudad(2, "Córdoba", 0.0, 0.0))
            empresaDao.insert(
                Empresa(1, "Fono Bus", R.drawable.fonobus_te_lleva),
                Empresa(2, "Grupo FAM", R.drawable.grupo_fam))
            horarioDao.insert(
                Horario(0, "13:30", "15:30", 1, 1, 2, "Córdoba", "Horario 1"),
                Horario(0, "14:30", "15:30", 2, 2, 1, "Dean Funes", "Horario 2")
            )
        }
    }
}
