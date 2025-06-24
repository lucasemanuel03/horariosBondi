package com.lucas.mibondiya.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lucas.mibondiya.data.dao.CiudadDao
import com.lucas.mibondiya.data.dao.EmpresaDao
import com.lucas.mibondiya.data.dao.HorarioDao
import com.lucas.mibondiya.data.model.Ciudad
import com.lucas.mibondiya.data.model.Empresa
import com.lucas.mibondiya.data.model.Horario

@Database(entities = [Empresa::class, Ciudad::class, Horario::class], version = 1 )
abstract class AppDatabase : RoomDatabase(){

    abstract fun empresaDao() : EmpresaDao
    abstract fun ciudadDao() : CiudadDao
    abstract fun horarioDao() : HorarioDao
}