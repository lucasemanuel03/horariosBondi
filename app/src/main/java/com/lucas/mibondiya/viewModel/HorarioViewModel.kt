package com.lucas.mibondiya.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucas.mibondiya.data.dao.HorarioCompletoDao
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
    private val horarioCompletoDao: HorarioCompletoDao
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

    init {
        viewModelScope.launch {
            val count = horarioCompletoDao.countHorarios()
            android.util.Log.d("VM_DB", "Cantidad de registros en la tabla: $count")
        }
    }
}


