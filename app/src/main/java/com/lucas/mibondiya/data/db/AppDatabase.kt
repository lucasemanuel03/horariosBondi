package com.lucas.mibondiya.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lucas.mibondiya.data.dao.CiudadDao
import com.lucas.mibondiya.data.dao.EmpresaDao
import com.lucas.mibondiya.data.dao.HorarioCompletoDao
import com.lucas.mibondiya.data.dao.HorarioDao
import com.lucas.mibondiya.data.model.Ciudad
import com.lucas.mibondiya.data.model.Empresa
import com.lucas.mibondiya.data.model.Horario


@Database(entities = [Horario::class, Ciudad::class, Empresa::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun horarioDao(): HorarioDao
    abstract fun ciudadDao(): CiudadDao
    abstract fun empresaDao(): EmpresaDao
    abstract fun horarioCompletoDao(): HorarioCompletoDao

}