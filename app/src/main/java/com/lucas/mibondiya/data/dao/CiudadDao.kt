package com.lucas.mibondiya.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucas.mibondiya.data.model.Ciudad
import kotlinx.coroutines.flow.Flow

@Dao
interface CiudadDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg ciudades : Ciudad)

    //GETS
    @Query("SELECT * FROM  ciudades")
    fun getAll() : Flow<List<Ciudad>>

    @Query("SELECT * FROM ciudades WHERE id=:id")
    suspend fun getById(id : Int) : Ciudad
}