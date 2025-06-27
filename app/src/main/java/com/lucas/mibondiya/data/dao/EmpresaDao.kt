package com.lucas.mibondiya.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucas.mibondiya.data.model.Empresa
import kotlinx.coroutines.flow.Flow

@Dao
interface EmpresaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg empresas: Empresa)

    @Query("SELECT * FROM empresas")
    fun getAll() : Flow<List<Empresa>>

    @Query("SELECT * FROM empresas WHERE id=:id")
    suspend fun getById(id : Int) : Empresa


}