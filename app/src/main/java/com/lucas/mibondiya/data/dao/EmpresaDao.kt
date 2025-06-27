package com.lucas.mibondiya.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucas.mibondiya.data.model.Empresa

@Dao
interface EmpresaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg empresas: Empresa)

    @Query("SELECT * FROM empresas")
    fun getAll() : Array<Empresa>

    @Query("SELECT * FROM empresas WHERE id=:id")
    fun getById(id : Int) : Empresa


}