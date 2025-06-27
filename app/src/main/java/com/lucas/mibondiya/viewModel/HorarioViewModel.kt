package com.lucas.mibondiya.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucas.mibondiya.data.dao.HorarioCompletoDao
import com.lucas.mibondiya.data.dao.HorarioDao
import com.lucas.mibondiya.data.model.Horario
import com.lucas.mibondiya.data.model.HorarioCompleto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HorarioViewModel @Inject constructor(
    private val horarioCompletoDao: HorarioCompletoDao,
    private val horarioDao: HorarioDao
) : ViewModel() {

    private val _ciudadFinId = MutableStateFlow<Int?>(null)

    val horarios: StateFlow<List<HorarioCompleto>> = _ciudadFinId
        .flatMapLatest { ciudadFinId ->
            if (ciudadFinId == null) {
                horarioCompletoDao.getHorariosCompletos()
            } else {
                horarioCompletoDao.getAllByIdCiudadFin(ciudadFinId)
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun setCiudadFin(id: Int?) {
        _ciudadFinId.value = id
    }

    fun insertarHorario(
        horaSalida: String,
        horaLlegada: String,
        empresaId: Int,
        ciudadInicioId: Int,
        ciudadDestinoId: Int,
        seAnuncia: String,
        notas: String
    ) {
        viewModelScope.launch {
            val nuevoHorario = Horario(
                id = 0, // autogenerado
                horaSalida = horaSalida,
                horaLlegada = horaLlegada,
                empresa_id = empresaId,
                ciudad_inicio_id = ciudadInicioId,
                ciudad_fin_id = ciudadDestinoId,
                seAnuncia = seAnuncia,
                notas = notas
            )

            horarioDao.insert(nuevoHorario)
        }
    }

    fun eliminarHorarioPorId(horarioId: Int, onResultado: (Boolean) -> Unit) {
        viewModelScope.launch {
            val filasAfectadas = horarioDao.deleteById(horarioId)
            onResultado(filasAfectadas > 0)
        }
    }

    fun editarHorario(horarioId: Int,
                      idEmpresa: Int,
                      horaDeSalida: String,
                      horaDeLlegada: String,
                      seAnuncia: String,
                      nota: String,
                      onResultado: (Boolean) -> Unit) {

        viewModelScope.launch {
            val filasAfectadas = horarioDao.updateById(horarioId, idEmpresa, horaDeSalida, horaDeLlegada, seAnuncia, nota)
            onResultado(filasAfectadas > 0)
        }
    }

    fun getHorarioById(
        horarioId: Int,
        onResultado: (Horario?) -> Unit
    ) {
        viewModelScope.launch {
            val resultado = horarioDao.getById(horarioId)
            onResultado(resultado)
        }
    }



    //init ==> es una funcion automática que se ejecuta al instanciar la clase
    init {
        viewModelScope.launch {
            val count = horarioCompletoDao.countHorarios()
            android.util.Log.d("VM_DB", "Cantidad de registros en la tabla: $count")
        }
    }
}


