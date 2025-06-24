package com.lucas.mibondiya.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucas.mibondiya.data.model.Ciudad

@Dao
interface CiudadDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCiudad(vararg ciudades : Ciudad)

    //GETS
    @Query("SELECT * FROM  ciudades")
    fun getAll() : List<Ciudad>

    @Query("SELECT * FROM ciudades WHERE id=:id")
    fun getById(id : Int) : List<Ciudad>
}