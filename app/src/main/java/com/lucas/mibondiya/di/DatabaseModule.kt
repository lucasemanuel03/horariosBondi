package com.lucas.mibondiya.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

import com.lucas.mibondiya.data.dao.CiudadDao
import com.lucas.mibondiya.data.dao.EmpresaDao
import com.lucas.mibondiya.data.dao.HorarioCompletoDao
import com.lucas.mibondiya.data.dao.HorarioDao
import com.lucas.mibondiya.data.db.AppDatabase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase {
        Log.d("DB_CREATE", "Creando base de datos...")
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // Para correr código al crear la base de datos, obtené la instancia real aquí:
                // Esta instancia se puede obtener con Room.databaseBuilder().build() pero aquí
                // no está accesible la instancia directamente. Para evitarlo, se puede hacer:
                CoroutineScope(Dispatchers.IO).launch {
                    // Para obtener la base de datos que está siendo creada:
                    val database = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "app_database"
                    ).build()


                }
            }
        }).build()
    }

    @Provides
    fun provideHorarioDao(db: AppDatabase): HorarioDao = db.horarioDao()

    @Provides
    fun provideHorarioCompletoDao(db: AppDatabase): HorarioCompletoDao = db.horarioCompletoDao()

    @Provides
    fun provideEmpresaDao(db: AppDatabase): EmpresaDao = db.empresaDao()

    @Provides
    fun provideCiudadDao(db: AppDatabase): CiudadDao = db.ciudadDao()
}
